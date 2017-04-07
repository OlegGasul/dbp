package de.mexchange.packagingdb.controller.user.object.container;


import java.util.List;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.exception.ReportGenerationException;
import de.mexchange.packagingdb.common.util.CollectionHelper;
import de.mexchange.packagingdb.common.util.export.ExportType;
import de.mexchange.packagingdb.common.util.export.pdf.exporter.PDFExporter;
import de.mexchange.packagingdb.common.util.export.pdf.exporter.PDFExporterFactory;
import de.mexchange.packagingdb.controller.response.AjaxResponse;
import de.mexchange.packagingdb.controller.response.ListAjaxResponse;
import de.mexchange.packagingdb.domain.Container;
import de.mexchange.packagingdb.service.MessageService;
import de.mexchange.packagingdb.service.ModelService;

public abstract class BaseContainerController<T> {

    protected MessageService messageService;

    protected ModelService<T> modelService;
    private String requestPath;
    private Class clazz;

    public BaseContainerController(ModelService<T> modelService,
            MessageService messageService,
            Class clazz) {

        this.modelService = modelService;
        this.messageService = messageService;
        requestPath = clazz.getSimpleName().toLowerCase();
        this.clazz = clazz;
    }

    public String addView(T t) {
        return String.format("/%s/add", requestPath);
    }

    public String add(T t, BindingResult result, RedirectAttributes redirectAttributes) throws InternalErrorException {
        return add(t, result, null, redirectAttributes);
    }

    public String add(T t, BindingResult result, Model model, RedirectAttributes redirectAttributes) throws InternalErrorException {

        // doing additional validation
        validate(t, result);

        if (result.hasErrors()) {
            if (model != null) {
                prepare(t, model);
            }
            return String.format("/%s/add", requestPath);
        }


        modelService.add(t);
        String message = messageService.getMessage(String.format("msg.%s.object.added", requestPath));
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:list";
    }

    public String list(Model model) throws InternalErrorException {
        long listCount = modelService.getListCount();
        model.addAttribute("dataCount", listCount);
        return String.format("/%s/list", requestPath);
    }

    protected AjaxResponse listItems(int page, int count) {
        try {
            long listCount = modelService.getListCount();
            if (page * 10L > listCount) {
                page = (int) (listCount / 10);
                if (listCount % 10 > 0) {
                    page += 1;
                }
            }

            List<T> list = modelService.getList(page, count);
            return new ListAjaxResponse("success", list);
        } catch (Exception e) {
            return new AjaxResponse("error", "Failed to retrieve list items");
        }
    }


    public String editView(Long id, Model model) throws InternalErrorException, DataNotFoundException {
        T t = modelService.getByID(id);
        model.addAttribute(requestPath, t);
        return String.format("/%s/edit", requestPath);
    }

    public String edit(T t, BindingResult result, RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {
        return edit(t, result, null, redirectAttributes);
    }

    public String edit(T t, BindingResult result, Model model, RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        // doing additional validation
        validate(t, result);

        if (result.hasErrors()) {
            if (model != null) {
                prepare(t, model);
            }
            return String.format("/%s/edit", requestPath);
        }

        modelService.edit(t);

        String message = messageService.getMessage(String.format("msg.%s.object.edited", requestPath));
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:list";
    }

    public String editPhotosView(Long id, Model model) throws InternalErrorException, DataNotFoundException {
        T t = modelService.getByID(id);
        model.addAttribute(requestPath, t);
        return String.format("/%s/edit-photos", requestPath);
    }

    public String editPhotos(T t, BindingResult result, RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        if (result.hasErrors()) {
            return String.format("/%s/edit-photos", requestPath);
        }

        modelService.edit(t);

        String message = messageService.getMessage(String.format("msg.%s.object.edited", requestPath));
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:list";
    }

    public String remove(List<Long> ids, RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        if (CollectionHelper.isEmpty(ids)) {
            redirectAttributes.addFlashAttribute("message", "No item selected to be removed");
            return "redirect:list";
        }

        modelService.deleteByIDs(ids);
        String message;
        if (ids.size() > 1) {
            message = messageService.getMessage(String.format("msg.%s.objects.removed", requestPath), ids.size());
        } else {
            message = messageService.getMessage(String.format("msg.%s.object.removed", requestPath));
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:list";
    }

    public void export(Long containerID, ExportType exportType) throws InternalErrorException, DataNotFoundException {
        PDFExporter exporter = PDFExporterFactory.getExporter(exportType, messageService);
        try {
            exporter.export((Container) modelService.getByID(containerID));
        } catch (ReportGenerationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overridden in subclass for additional validations
     * 
     * @param t
     * @param result
     */
    protected abstract void validate(T t, BindingResult result);

    /**
     * Overridden in subclass for additional data initializing
     */
    protected abstract void prepare(T t, Model model) throws InternalErrorException;
}
