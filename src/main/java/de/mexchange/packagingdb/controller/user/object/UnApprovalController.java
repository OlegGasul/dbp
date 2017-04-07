package de.mexchange.packagingdb.controller.user.object;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.controller.response.AjaxResponse;
import de.mexchange.packagingdb.domain.AggregateState;
import de.mexchange.packagingdb.domain.Company;
import de.mexchange.packagingdb.domain.UnApproval;
import de.mexchange.packagingdb.service.LanguageService;
import de.mexchange.packagingdb.service.MessageService;
import de.mexchange.packagingdb.service.UnApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by Serozh on 6/16/16.
 */
@Controller
@RequestMapping("/unapproval")
public class UnApprovalController extends BaseObjectController<UnApproval> {

    private UnApprovalService unApprovalService;
    private Validator validator;

    @Autowired
    public UnApprovalController(UnApprovalService unApprovalService, MessageService messageService, LanguageService languageService) {
        super(unApprovalService, messageService, languageService, UnApproval.class);
        this.unApprovalService = unApprovalService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (validator == null) {
            validator = binder.getValidator();
        }
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(UnApproval unApproval) {
        return super.addView(unApproval);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(final UnApproval unapproval,
                      BindingResult result,
                      @RequestParam(value = "file2", required = false) MultipartFile file,
                      final RedirectAttributes redirectAttributes) throws InternalErrorException {

        validator.validate(unapproval, result);

        if (file != null && !file.isEmpty()) {
            try {
                unapproval.setFile(file.getBytes());
                unapproval.setFileContentType(file.getContentType());
                unapproval.setFileFilename(file.getOriginalFilename());
            } catch (IOException e) {
                result.addError(new FieldError("unApproval", "fileFilename", "Failed to attach file"));
            }
        }

        return super.add(unapproval, result, redirectAttributes);
    }

    @RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("id") Long id,
                              HttpServletResponse response) throws InternalErrorException, DataNotFoundException, IOException {
        UnApproval approval = unApprovalService.getByID(id);
        response.setContentType(approval.getFileContentType());
        response.setHeader("Content-disposition", "attachment; filename=" + approval.getFileFilename());
        response.getOutputStream().write(approval.getFile());
        response.flushBuffer();
    }

    @PreAuthorize("hasRole('ROLE_READER') or hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws InternalErrorException {
        return super.list(model);
    }

    @PreAuthorize("hasRole('ROLE_READER') or hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/list-items", method = RequestMethod.GET)
    public @ResponseBody
    AjaxResponse listItems(@RequestParam(value = "page", required = false) int page,
                           @RequestParam(value = "count", required = false, defaultValue = "10") int count) {
        return super.listItems(page, count);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id,
                       Model model) throws InternalErrorException, DataNotFoundException {

        return super.editView(id, model);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(final UnApproval unApproval,
                       BindingResult result,
                       @RequestParam(value = "fileRemoved", required = false) boolean fileRemoved,
                       @RequestParam(value = "file2", required = false) MultipartFile file,
                       final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        validator.validate(unApproval, result);
        if (fileRemoved) {
            unApproval.setFileFilename(null);
        } else if (!file.isEmpty()) {
            try {
                unApproval.setFile(file.getBytes());
                unApproval.setFileContentType(file.getContentType());
                unApproval.setFileFilename(file.getOriginalFilename());
            } catch (IOException e) {
                result.addError(new FieldError("unApproval", "imageFilename", "Failed to attach file"));
            }
        }
        return super.edit(unApproval, result, redirectAttributes);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam(value = "id", required = false) List<Long> ids,
                         final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {
        return super.remove(ids, redirectAttributes);
    }

    @Override
    protected void validate(UnApproval unApproval, BindingResult result) {
        Company company = unApproval.getCompany();
        if (company == null || company.getId() == null) {
            result.addError(new FieldError("unApproval", "company",
                    messageService.getMessage("err.field.approval.company.required")));
        }
    }
}
