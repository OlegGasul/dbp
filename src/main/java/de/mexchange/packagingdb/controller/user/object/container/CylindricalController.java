package de.mexchange.packagingdb.controller.user.object.container;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.CollectionHelper;
import de.mexchange.packagingdb.common.util.ImageHelper;
import de.mexchange.packagingdb.controller.response.AjaxResponse;
import de.mexchange.packagingdb.domain.ContainerPhoto;
import de.mexchange.packagingdb.domain.Cylindrical;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * User: Garik
 * Date: 6/15/16
 * Time: 11:36 PM
 */
@Controller
@RequestMapping("/cylindrical")
public class CylindricalController extends BaseContainerController<Cylindrical> {

    private Validator validator;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ExZoneService exZoneService;

    @Autowired
    private BusinessUnitService businessUnitService;

    @Autowired
    private CoatingService coatingService;

    @Autowired
    private ClosureService closureService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private CouplingService couplingService;

    @Autowired
    private PaletteTypeService paletteTypeService;

    @Autowired
    private CompanyService companyService;


    @Autowired
    public CylindricalController(CylindricalService cylindricalService, MessageService messageService) {
        super(cylindricalService, messageService, Cylindrical.class);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (validator == null) {
            validator = binder.getValidator();
        }
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Cylindrical cylindrical, Model model) throws InternalErrorException {
        prepare(cylindrical, model);
        return super.addView(cylindrical);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(final Cylindrical cylindrical, Model model,
                      @RequestParam(value = "infoPhotos", required = false) List<MultipartFile> photos,
                      BindingResult result,
                      @RequestParam(value = "file1", required = false) MultipartFile specifications,
                      @RequestParam(value = "file2", required = false) MultipartFile drawing,
                      @RequestParam(value = "file3", required = false) MultipartFile drawingCAD,
                      final RedirectAttributes redirectAttributes) throws InternalErrorException {

        validator.validate(cylindrical, result);
        if (!result.hasErrors() && CollectionHelper.isNotEmpty(photos)) {
            List<ContainerPhoto> photoList = new ArrayList<>();
            try {
                for (MultipartFile file : photos) {
                    if (!file.isEmpty()) {
                        if (!ImageHelper.isImageContentType(file.getContentType())) {
                            result.addError(new FieldError("cylindrical", "photos",
                                    messageService.getMessage("err.msg.image.type.required")));
                            break;
                        }
                        ContainerPhoto photo = new ContainerPhoto();
                        photo.setData(file.getBytes());
                        photo.setContentType(file.getContentType());
                        photo.setFilename(file.getOriginalFilename());
                        photoList.add(photo);
                    }
                }
                if (!result.hasErrors() && CollectionHelper.isNotEmpty(photoList)) {
                    cylindrical.setPhotos(photoList);
                }
            } catch (IOException e) {
                result.addError(new FieldError("cylindrical", "photos", "Failed to attach photos")); //TODO add appropriate message
            }
        }

        if (!result.hasErrors() && !specifications.isEmpty()) {
            try {
                cylindrical.setManufacturerSpecifications(specifications.getBytes());
                cylindrical.setManufacturerSpecificationsContentType(specifications.getContentType());
                cylindrical.setManufacturerSpecificationsFilename(specifications.getOriginalFilename());
            } catch (IOException e) {
                result.addError(new FieldError("cylindrical", "manufacturerSpecificationsFilename",   messageService.getMessage("err.msg.file.attach.failed", "Manufacturer Specifications")));
            }
        }
        if (!result.hasErrors() && !drawing.isEmpty()) {
            try {
                cylindrical.setDrawing(drawing.getBytes());
                cylindrical.setDrawingContentType(drawing.getContentType());
                cylindrical.setDrawingFilename(drawing.getOriginalFilename());
            } catch (IOException e) {
                result.addError(new FieldError("cylindrical", "drawingFilename",  messageService.getMessage("err.msg.file.attach.failed", "Drawing")));
            }
        }
        if (!result.hasErrors() && !drawingCAD.isEmpty()) {
            try {
                cylindrical.setDrawingCAD(drawingCAD.getBytes());
                cylindrical.setDrawingCADContentType(drawingCAD.getContentType());
                cylindrical.setDrawingCADFilename(drawingCAD.getOriginalFilename());
            } catch (IOException e) {
                result.addError(new FieldError("cylindrical", "drawingCADFilename", messageService.getMessage("err.msg.file.attach.failed", "Drawing CAD")));
            }
        }

        if (result.hasErrors() && model != null) {
            prepare(cylindrical, model);
        }
        
        return super.add(cylindrical, result, redirectAttributes);
    }


    @RequestMapping(value = "/specifications/{id}", method = RequestMethod.GET)
    public void downloadSpecifications(@PathVariable("id") Long id,
                                       HttpServletResponse response) throws InternalErrorException, DataNotFoundException, IOException {
        Cylindrical data = modelService.getByID(id);
        response.setContentType(data.getManufacturerSpecificationsContentType());
        response.setHeader("Content-disposition", "attachment; filename=" + data.getManufacturerSpecificationsFilename());
        response.getOutputStream().write(data.getManufacturerSpecifications());
        response.flushBuffer();
    }

    @RequestMapping(value = "/drawing/{id}", method = RequestMethod.GET)
    public void downloadDrawing(@PathVariable("id") Long id,
                                HttpServletResponse response) throws InternalErrorException, DataNotFoundException, IOException {
        Cylindrical data = modelService.getByID(id);
        response.setContentType(data.getDrawingContentType());
        response.setHeader("Content-disposition", "attachment; filename=" + data.getDrawingFilename());
        response.getOutputStream().write(data.getDrawing());
        response.flushBuffer();
    }

    @RequestMapping(value = "/drawingcad/{id}", method = RequestMethod.GET)
    public void downloadDrawingCAD(@PathVariable("id") Long id,
                                   HttpServletResponse response) throws InternalErrorException, DataNotFoundException, IOException {
        Cylindrical data = modelService.getByID(id);
        response.setContentType(data.getDrawingCADContentType());
        response.setHeader("Content-disposition", "attachment; filename=" + data.getDrawingCADFilename());
        response.getOutputStream().write(data.getDrawingCAD());
        response.flushBuffer();
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

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id,
                       Model model) throws InternalErrorException, DataNotFoundException {
        prepare(model);
        return super.editView(id, model);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid final Cylindrical cylindrical, Model model,
                       BindingResult result,
                       @RequestParam(value = "specificationsRemoved", required = false) boolean specificationsRemoved,
                       @RequestParam(value = "fileSpecifications", required = false) MultipartFile specifications,
                       @RequestParam(value = "drawingRemoved", required = false) boolean drawingRemoved,
                       @RequestParam(value = "fileDrawing", required = false) MultipartFile drawing,
                       @RequestParam(value = "drawingCADRemoved", required = false) boolean drawingCADRemoved,
                       @RequestParam(value = "fileDrawingCAD", required = false) MultipartFile drawingCAD,
                       final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        validator.validate(cylindrical, result);
        if (specificationsRemoved) {
            cylindrical.setManufacturerSpecificationsFilename(null);
        } else if (!result.hasErrors() && specifications != null && !specifications.isEmpty()) {
            try {
                cylindrical.setManufacturerSpecifications(specifications.getBytes());
                cylindrical.setManufacturerSpecificationsContentType(specifications.getContentType());
                cylindrical.setManufacturerSpecificationsFilename(specifications.getOriginalFilename());
            } catch (IOException e) {
                String fieldName = messageService.getMessage("label.container.manufacturer.specifications");
                result.addError(new FieldError("bigBags", "manufacturerSpecificationsFilename",   messageService.getMessage("err.msg.file.attach.failed", fieldName)));
            }
        }
        if (drawingRemoved) {
            cylindrical.setDrawingFilename(null);
        } else if (!result.hasErrors() && drawing != null && !drawing.isEmpty()) {
            try {
                cylindrical.setDrawing(drawing.getBytes());
                cylindrical.setDrawingContentType(drawing.getContentType());
                cylindrical.setDrawingFilename(drawing.getOriginalFilename());
            } catch (IOException e) {
                String fieldName = messageService.getMessage("label.container.drawing");
                result.addError(new FieldError("bigBags", "drawingFilename", messageService.getMessage("err.msg.file.attach.failed", fieldName)));
            }
        }
        if (drawingCADRemoved) {
            cylindrical.setDrawingCAD(null);
        } else if (!result.hasErrors() && drawingCAD != null && !drawingCAD.isEmpty()) {
            try {
                cylindrical.setDrawingCAD(drawingCAD.getBytes());
                cylindrical.setDrawingCADContentType(drawingCAD.getContentType());
                cylindrical.setDrawingCADFilename(drawingCAD.getOriginalFilename());
            } catch (IOException e) {
                String fieldName = messageService.getMessage("label.container.drawing.cad");
                result.addError(new FieldError("bigBags", "drawingCADFilename", messageService.getMessage("err.msg.file.attach.failed", fieldName)));
            }
        }

        if (result.hasErrors() && model != null) {
            prepare(cylindrical, model);
        }

        return super.edit(cylindrical, result, redirectAttributes);
    }


    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit-photos/{id}", method = RequestMethod.GET)
    public String editPhotosView(@PathVariable("id") Long id,
                                 Model model) throws InternalErrorException, DataNotFoundException {
        return super.editPhotosView(id, model);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
    public void viewPhoto(HttpServletResponse response,
                          @PathVariable("id") Long photoID) throws InternalErrorException, DataNotFoundException, IOException {

        CylindricalService service = getService();
        ContainerPhoto photo = service.getPhotoByID(photoID);
        response.setContentType(photo.getContentType());
        response.setHeader("Content-disposition", "attachment; filename=" + photo.getFilename());
        response.getOutputStream().write(photo.getData());
        response.flushBuffer();
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/photo-add/{id}", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody AjaxResponse addPhoto(@PathVariable("id") Long cylindricalID,
                                               @RequestParam("file_data") MultipartFile file)
            throws InternalErrorException, DataNotFoundException, IOException {

        try {
            if (file.isEmpty()) {
                String error = messageService.getMessage("err.msg.image.empty", file.getContentType());
                return new AjaxResponse("error", error);
            }
            if (!ImageHelper.isImageContentType(file.getContentType())) {
                String error = messageService.getMessage("err.msg.image.type.not.supported", file.getContentType());
                return new AjaxResponse("error", error);
            }

            ContainerPhoto photo = new ContainerPhoto();
            photo.setData(file.getBytes());
            photo.setContentType(file.getContentType());
            photo.setFilename(file.getOriginalFilename());
            CylindricalService service = getService();
            service.add(cylindricalID, photo);
        } catch (Exception e) {
            String error = messageService.getMessage("err.msg.image.upload.failed");
            return new AjaxResponse("error", error);
        }
        return new AjaxResponse("success", "");
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/remove-photo/{id}", method = {RequestMethod.POST, RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody AjaxResponse deletePhoto(@PathVariable("id") Long id) throws InternalErrorException, DataNotFoundException {
        try {
            CylindricalService service = getService();
            service.deletePhotoByID(id);
        } catch (Exception e) {
            String error =  messageService.getMessage("err.msg.image.remove.failed");
            return  new AjaxResponse("error", error);
        }
        return new AjaxResponse("success", "");
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit-photos", method = RequestMethod.POST)
    public String editPhotos(@Valid final Cylindrical cylindrical,
                             @RequestParam(value = "infoPhotos", required = false) List<MultipartFile> photos,
                             BindingResult result,
                             final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        return super.editPhotos(cylindrical, result, redirectAttributes);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam(value = "id", required = false) List<Long> ids,
                         final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        return super.remove(ids, redirectAttributes);
    }

    @Override
    protected void validate(Cylindrical cylindrical, BindingResult result) {
        if (cylindrical.getOutletArmatur() == null || cylindrical.getOutletArmatur().getId() == null) {
            result.addError(new FieldError("cylindrical", "outletArmatur",
                    messageService.getMessage("err.msg.cylindrical.outlet.armatur.required")));
        }

        if (cylindrical.getContainerMaterial() == null || cylindrical.getContainerMaterial().getId() == null) {
            result.addError(new FieldError("cylindrical", "containerMaterial",
                    messageService.getMessage("err.msg.cylindrical.container.material.required")));
        }

        if (cylindrical.getFrameMaterial() == null || cylindrical.getFrameMaterial().getId() == null) {
            result.addError(new FieldError("cylindrical", "frameMaterial",
                    messageService.getMessage("err.msg.cylindrical.frame.material.required")));
        }

        if (cylindrical.getHole() == null || cylindrical.getHole().getId() == null) {
            result.addError(new FieldError("cylindrical", "hole",
                    messageService.getMessage("err.msg.cylindrical.hole.required")));
        }
    }

    private CylindricalService getService() {
        return (CylindricalService) modelService;
    }

    protected void prepare(Cylindrical cylindrical, Model model) throws InternalErrorException {
        prepare(model);
    }

    protected void prepare(Model model) throws InternalErrorException {
        Locale locale = LocaleContextHolder.getLocale();
        model.addAttribute("locations", locationService.getAll(Language.localeOf(locale)));
        model.addAttribute("businessunits", businessUnitService.getAll(Language.localeOf(locale)));
        model.addAttribute("materials", materialService.getAll(Language.localeOf(locale)));
        model.addAttribute("palettetypes", paletteTypeService.getAll(Language.localeOf(locale)));
        model.addAttribute("closures", closureService.getAll(Language.localeOf(locale)));
        model.addAttribute("couplings", couplingService.getAll(Language.localeOf(locale)));
        model.addAttribute("exzones", exZoneService.getAll(Language.localeOf(locale)));
        model.addAttribute("coatings", coatingService.getAll(Language.localeOf(locale)));
        model.addAttribute("companies", companyService.getAll(Language.localeOf(locale)));
    }
}
