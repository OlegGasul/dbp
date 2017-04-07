package de.mexchange.packagingdb.controller.user.object.process;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.controller.response.AjaxResponse;
import de.mexchange.packagingdb.controller.response.ListAjaxResponse;
import de.mexchange.packagingdb.controller.user.object.BaseObjectController;
import de.mexchange.packagingdb.domain.Process;
import de.mexchange.packagingdb.domain.lcp.ProcessType;
import de.mexchange.packagingdb.service.LanguageService;
import de.mexchange.packagingdb.service.MessageService;
import de.mexchange.packagingdb.service.ProcessService;

/**
 * Created by Serozh on 6/8/2016.
 */
public abstract class BaseProcessController extends BaseObjectController<Process> {

    protected ProcessType processType;

    protected Validator validator;

    protected ProcessService processService;

    public BaseProcessController(ProcessService processService, MessageService messageService, ProcessType processType, LanguageService languageService) {
        super(processService, messageService, languageService, Process.class);
        this.processType = processType;
        this.processService = processService;
        setRequestPath(processType == ProcessType.GLOBAL_PROCESS ? "globalprocess" : "globalinformation");
    }

    public String add(Process process, Model model) {
        process.setProcessType(processType);
        return super.addView(process);
    }

    public String add(Process process,
            MultipartFile processFile,
            BindingResult result,
            RedirectAttributes redirectAttributes) throws InternalErrorException {
        process.setProcessType(processType);
        validator.validate(process, result);
        if (!result.hasErrors() && !processFile.isEmpty()) {
            try {
                process.setProcessFile(processFile.getBytes());
                process.setProcessFileContentType(processFile.getContentType());
                process.setProcessFilename(processFile.getOriginalFilename());
            } catch (IOException e) {
                result.addError(new FieldError("process", "processFilename", "Failed to attach process file"));
            }
        }
        return super.add(process, result, redirectAttributes);
    }

    @Override
    public String list(Model model) throws InternalErrorException {
        model.addAttribute("processType", processType);
        long listCount = processService.getListCount(processType);
        model.addAttribute("dataCount", listCount);
        return String.format("/%s/list", getRequestPath());
    }

    @Override
    protected AjaxResponse listItems(int page, int count) {
        try {
            long listCount = processService.getListCount(processType);
            if (page * 10L > listCount) {
                page = (int) (listCount / 10);
                if (listCount % 10 > 0) {
                    page += 1;
                }
            }

            List<Process> list = processService.getList(page, count, processType);
            return new ListAjaxResponse("success", list);
        } catch (Exception e) {
            return new AjaxResponse("error", "Failed to retrieve list items");
        }
    }

    public void downloadProcessFile(Long id, HttpServletResponse response)
            throws InternalErrorException, DataNotFoundException, IOException {

        Process process = modelService.getByID(id);
        response.setContentType(process.getProcessFileContentType());
        response.setHeader("Content-disposition", "attachment; filename=" + process.getProcessFilename());
        response.getOutputStream().write(process.getProcessFile());
        response.flushBuffer();
    }

    public String edit(Long id, Model model) throws InternalErrorException, DataNotFoundException {
        model.addAttribute("processType", processType);
        return super.editView(id, model);
    }

    public String edit(Process process,
            boolean processFileRemoved,
            MultipartFile processFile,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes)
                    throws DataNotFoundException, InternalErrorException {
        process.setProcessType(processType);
        validator.validate(process, result);
        if (processFileRemoved) {
            process.setProcessFilename(null);
        } else if (!result.hasErrors() && !processFile.isEmpty()) {
            try {
                process.setProcessFile(processFile.getBytes());
                process.setProcessFileContentType(processFile.getContentType());
                process.setProcessFilename(processFile.getOriginalFilename());
            } catch (IOException e) {
                result.addError(new FieldError("process", "processFile", "Failed to attach process file"));
            }
        }
        return super.edit(process, result, model,redirectAttributes);
    }

    @Override
    public String remove(List<Long> ids, RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {
        return super.remove(ids, redirectAttributes);
    }

    @Override
    protected void prepare(Process process, Model model) throws InternalErrorException {
        model.addAttribute("processType", processType);
    }
}
