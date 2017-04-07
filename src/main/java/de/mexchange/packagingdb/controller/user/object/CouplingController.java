package de.mexchange.packagingdb.controller.user.object;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.CollectionHelper;
import de.mexchange.packagingdb.controller.response.AjaxResponse;
import de.mexchange.packagingdb.domain.Coupling;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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
import java.util.Locale;

/**
 * Created by Arthur on 5/30/2016.
 */
@Controller
@RequestMapping("/coupling")
public class CouplingController extends BaseObjectController<Coupling> {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private GasketService gasketService;

    private CouplingService couplingService;
    private Validator validator;

	@Autowired
	public CouplingController(CouplingService couplingService, MessageService messageService, LanguageService languageService) {
		super(couplingService, messageService, languageService, Coupling.class);
        this.couplingService = couplingService;
	}

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (validator == null) {
            validator = binder.getValidator();
        }
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Coupling coupling, Model model) throws InternalErrorException {
        prepare(coupling, model);
        return super.addView(coupling);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(final Coupling coupling, Model model,
                      BindingResult result,
                      @RequestParam(value = "file", required = false) MultipartFile image,
                      @RequestParam(value = "file2", required = false) MultipartFile drawing,
                      final RedirectAttributes redirectAttributes) throws InternalErrorException {

        validator.validate(coupling, result);

        if (!result.hasErrors()) {
            if (image != null && !image.isEmpty()) {
                try {
                    coupling.setImage(image.getBytes());
                    coupling.setImageContentType(image.getContentType());
                    coupling.setImageFilename(image.getOriginalFilename());
                } catch (IOException e) {
                    result.addError(new FieldError("image", "imageFilename", "Failed to attach image file"));
                }
            }
            if (drawing != null && !drawing.isEmpty()) {
                try {
                    coupling.setDrawing(drawing.getBytes());
                    coupling.setDrawingContentType(drawing.getContentType());
                    coupling.setDrawingFilename(drawing.getOriginalFilename());
                } catch (IOException e) {
                    result.addError(new FieldError("drawing", "drawingFilename", "Failed to attach drawing file"));
                }
            }
        } else {
            if (model != null) {
                prepare(coupling, model);
            }
        }

        return super.add(coupling, result, redirectAttributes);
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public void downloadImage(@PathVariable("id") Long id,
                                   HttpServletResponse response) throws InternalErrorException, DataNotFoundException, IOException {
        Coupling coupling = couplingService.getByID(id);
        response.setContentType(coupling.getImageContentType());
        response.setHeader("Content-disposition", "attachment; filename=" + coupling.getImageFilename());
        response.getOutputStream().write(coupling.getImage());
        response.flushBuffer();
    }

    @RequestMapping(value = "/drawing/{id}", method = RequestMethod.GET)
    public void downloadDrawing(@PathVariable("id") Long id,
                                   HttpServletResponse response) throws InternalErrorException, DataNotFoundException, IOException {
        Coupling coupling = couplingService.getByID(id);
        response.setContentType(coupling.getDrawingContentType());
        response.setHeader("Content-disposition", "attachment; filename=" + coupling.getDrawingFilename());
        response.getOutputStream().write(coupling.getDrawing());
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
        prepare(model);
        return super.editView(id, model);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(final Coupling coupling, Model model,
                       @RequestParam(value = "imageRemoved", required = false) boolean imageRemoved,
                       @RequestParam(value = "file", required = false) MultipartFile image,
                       @RequestParam(value = "drawingRemoved", required = false) boolean drawingRemoved,
                       @RequestParam(value = "file2", required = false) MultipartFile drawing,
                       BindingResult result,
                       final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        validator.validate(coupling, result);

        if (!result.hasErrors()) {

            if (imageRemoved) {
                coupling.setImageFilename(null);
            } else if (!image.isEmpty()) {
                try {
                    coupling.setImage(image.getBytes());
                    coupling.setImageContentType(image.getContentType());
                    coupling.setImageFilename(image.getOriginalFilename());
                } catch (IOException e) {
                    result.addError(new FieldError("coupling", "imageFilename", "Failed to attach image file"));
                }
            }

            if (drawingRemoved) {
                coupling.setDrawingFilename(null);
            } else if (!image.isEmpty()) {
                try {
                    coupling.setDrawing(drawing.getBytes());
                    coupling.setDrawingContentType(drawing.getContentType());
                    coupling.setDrawingFilename(drawing.getOriginalFilename());
                } catch (IOException e) {
                    result.addError(new FieldError("coupling", "drawingFilename", "Failed to attach drawing file"));
                }
            }
        } else {
            if (model != null) {
                prepare(coupling, model);
            }
        }
        return super.edit(coupling, result, redirectAttributes);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam(value = "id", required = false) List<Long> ids,
                       final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        return super.remove(ids, redirectAttributes);
    }

    @Override
    protected void prepare(Coupling coupling, Model model) throws InternalErrorException {
        prepare(model);
    }

    @Override
    protected void prepare(Model model) throws InternalErrorException {
        Locale locale = LocaleContextHolder.getLocale();
        model.addAttribute("materials", materialService.getAll(Language.localeOf(locale)));
        model.addAttribute("gaskets", gasketService.getAll(Language.localeOf(locale)));
    }
}
