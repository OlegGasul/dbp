package de.mexchange.packagingdb.controller.user.object;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.controller.response.AjaxResponse;
import de.mexchange.packagingdb.domain.Coating;
import de.mexchange.packagingdb.service.CoatingService;
import de.mexchange.packagingdb.service.LanguageService;
import de.mexchange.packagingdb.service.MessageService;
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
import java.io.IOException;
import java.util.List;

/**
 * Created by Garik on 4/30/16.
 */
@Controller
@RequestMapping("/coating")
public class CoatingController extends BaseObjectController<Coating> {

    private Validator validator;

	@Autowired
	public CoatingController(CoatingService coatingService, LanguageService languageService, MessageService messageService) {
		super(coatingService, messageService, languageService, Coating.class);
	}

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (validator == null) {
            validator = binder.getValidator();
        }
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Coating coating) {
        return super.addView(coating);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(final Coating coating,
                      @RequestParam(value = "file", required = false) MultipartFile certDFC,
                      BindingResult result,
                      final RedirectAttributes redirectAttributes) throws InternalErrorException {
        validator.validate(coating, result);
        if (!result.hasErrors() && !certDFC.isEmpty()) {
            try {
                coating.setCertDFCF(certDFC.getBytes());
                coating.setCertDFCContentType(certDFC.getContentType());
                coating.setCertDFCFilename(certDFC.getOriginalFilename());
            } catch (IOException e) {
                result.addError(new FieldError("coating", "certDFCFilename", "Failed to attach certificate file"));
            }
        }
        return super.add(coating, result, redirectAttributes);
    }

    @PreAuthorize("hasRole('ROLE_READER') or hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws InternalErrorException {
        return super.list(model);
    }

    @PreAuthorize("hasRole('ROLE_READER') or hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/list-items", method = RequestMethod.GET)
    public @ResponseBody AjaxResponse listItems(@RequestParam(value = "page", required = false) int page,
                                                @RequestParam(value = "count", required = false, defaultValue = "10") int count) {
        return super.listItems(page, count);
    }

    @RequestMapping(value = "/certDFCF/{id}", method = RequestMethod.GET)
    public void downloadCertDFCF(@PathVariable("id") Long id,
                                 HttpServletResponse response) throws InternalErrorException, DataNotFoundException, IOException {
        Coating coating = modelService.getByID(id);
        response.setContentType(coating.getCertDFCContentType());
        response.setHeader("Content-disposition", "attachment; filename=" + coating.getCertDFCFilename());
        response.getOutputStream().write(coating.getCertDFCF());
        response.flushBuffer();
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id,
                       Model model) throws InternalErrorException, DataNotFoundException {

        return super.editView(id, model);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(final Coating coating,
                       @RequestParam(value = "certRemoved", required = false) boolean certRemoved,
                       @RequestParam(value = "file", required = false) MultipartFile certDFC,
                       BindingResult result,
                       final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        validator.validate(coating, result);
        if (certRemoved) {
            coating.setCertDFCFilename(null);
        } else if (!result.hasErrors() && !certDFC.isEmpty()) {
            try {
                coating.setCertDFCF(certDFC.getBytes());
                coating.setCertDFCContentType(certDFC.getContentType());
                coating.setCertDFCFilename(certDFC.getOriginalFilename());
            } catch (IOException e) {
                result.addError(new FieldError("coating", "certDFCFilename", "Failed to attach certificate file"));
            }
        }
        return super.edit(coating, result, redirectAttributes);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam(value = "id", required = false) List<Long> ids,
                       final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        return super.remove(ids, redirectAttributes);
    }
}
