package de.mexchange.packagingdb.controller.user.object.container;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.CollectionHelper;
import de.mexchange.packagingdb.common.util.ImageHelper;
import de.mexchange.packagingdb.controller.response.AjaxResponse;
import de.mexchange.packagingdb.domain.Bulk;
import de.mexchange.packagingdb.domain.ContainerPhoto;
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

@Controller
@RequestMapping("/bulk")
public class BulkController extends BaseContainerController<Bulk> {

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
    private MaterialService materialService;

    @Autowired
    private PaletteTypeService paletteTypeService;

    @Autowired
    private CouplingService couplingService;

    @Autowired
    private ClosureService closureService;

    @Autowired
    private CompanyService companyService;



    @Autowired
    public BulkController(BulkService bulkService, MessageService messageService) {
        super(bulkService, messageService, Bulk.class);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (validator == null) {
            validator = binder.getValidator();
        }
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Bulk bulk, Model model) throws InternalErrorException {
        prepare(bulk, model);
        return super.addView(bulk);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(final Bulk bulk, Model model,
                      @RequestParam(value = "infoPhotos", required = false) List<MultipartFile> photos,
                      BindingResult result,
                      @RequestParam(value = "file1", required = false) MultipartFile specifications,
                      @RequestParam(value = "file2", required = false) MultipartFile drawing,
                      @RequestParam(value = "file3", required = false) MultipartFile drawingCAD,
                      final RedirectAttributes redirectAttributes) throws InternalErrorException {

        validator.validate(bulk, result);
        if (!result.hasErrors() && CollectionHelper.isNotEmpty(photos)) {
            List<ContainerPhoto> photoList = new ArrayList<>();
            try {
                for (MultipartFile file : photos) {
                    if (!file.isEmpty()) {
                        if (!ImageHelper.isImageContentType(file.getContentType())) {
                            result.addError(new FieldError("bulk", "photos",
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
                    bulk.setPhotos(photoList);
                }
            } catch (IOException e) {
                result.addError(new FieldError("bulk", "photos", "Failed to attach photos")); //TODO add appropriate message
            }
        }

        if (!result.hasErrors() && !specifications.isEmpty()) {
            try {
                bulk.setManufacturerSpecifications(specifications.getBytes());
                bulk.setManufacturerSpecificationsContentType(specifications.getContentType());
                bulk.setManufacturerSpecificationsFilename(specifications.getOriginalFilename());
            } catch (IOException e) {
                result.addError(new FieldError("bulk", "manufacturerSpecificationsFilename",   messageService.getMessage("err.msg.file.attach.failed", "Manufacturer Specifications")));
            }
        }
        if (!result.hasErrors() && !drawing.isEmpty()) {
            try {
                bulk.setDrawing(drawing.getBytes());
                bulk.setDrawingContentType(drawing.getContentType());
                bulk.setDrawingFilename(drawing.getOriginalFilename());
            } catch (IOException e) {
                result.addError(new FieldError("bulk", "drawingFilename",  messageService.getMessage("err.msg.file.attach.failed", "Drawing")));
            }
        }
        if (!result.hasErrors() && !drawingCAD.isEmpty()) {
            try {
                bulk.setDrawingCAD(drawingCAD.getBytes());
                bulk.setDrawingCADContentType(drawingCAD.getContentType());
                bulk.setDrawingCADFilename(drawingCAD.getOriginalFilename());
            } catch (IOException e) {
                result.addError(new FieldError("bulk", "drawingCADFilename", messageService.getMessage("err.msg.file.attach.failed", "Drawing CAD")));
            }
        }

        if (result.hasErrors() && model != null) {
            prepare(bulk, model);
        }

        return super.add(bulk, result, redirectAttributes);
    }


    @RequestMapping(value = "/specifications/{id}", method = RequestMethod.GET)
    public void downloadSpecifications(@PathVariable("id") Long id,
                                       HttpServletResponse response) throws InternalErrorException, DataNotFoundException, IOException {
        Bulk data = modelService.getByID(id);
        response.setContentType(data.getManufacturerSpecificationsContentType());
        response.setHeader("Content-disposition", "attachment; filename=" + data.getManufacturerSpecificationsFilename());
        response.getOutputStream().write(data.getManufacturerSpecifications());
        response.flushBuffer();
    }

    @RequestMapping(value = "/drawing/{id}", method = RequestMethod.GET)
    public void downloadDrawing(@PathVariable("id") Long id,
                                HttpServletResponse response) throws InternalErrorException, DataNotFoundException, IOException {
        Bulk data = modelService.getByID(id);
        response.setContentType(data.getDrawingContentType());
        response.setHeader("Content-disposition", "attachment; filename=" + data.getDrawingFilename());
        response.getOutputStream().write(data.getDrawing());
        response.flushBuffer();
    }

    @RequestMapping(value = "/drawingcad/{id}", method = RequestMethod.GET)
    public void downloadDrawingCAD(@PathVariable("id") Long id,
                                   HttpServletResponse response) throws InternalErrorException, DataNotFoundException, IOException {
        Bulk data = modelService.getByID(id);
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
    public String edit(@Valid final Bulk bulk, Model model,
                       BindingResult result,
                       @RequestParam(value = "specificationsRemoved", required = false) boolean specificationsRemoved,
                       @RequestParam(value = "fileSpecifications", required = false) MultipartFile specifications,
                       @RequestParam(value = "drawingRemoved", required = false) boolean drawingRemoved,
                       @RequestParam(value = "fileDrawing", required = false) MultipartFile drawing,
                       @RequestParam(value = "drawingCADRemoved", required = false) boolean drawingCADRemoved,
                       @RequestParam(value = "fileDrawingCAD", required = false) MultipartFile drawingCAD,
                       final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        validator.validate(bulk, result);
        if (specificationsRemoved) {
            bulk.setManufacturerSpecificationsFilename(null);
        } else if (!result.hasErrors() && specifications != null && !specifications.isEmpty()) {
            try {
                bulk.setManufacturerSpecifications(specifications.getBytes());
                bulk.setManufacturerSpecificationsContentType(specifications.getContentType());
                bulk.setManufacturerSpecificationsFilename(specifications.getOriginalFilename());
            } catch (IOException e) {
                String fieldName = messageService.getMessage("label.container.manufacturer.specifications");
                result.addError(new FieldError("bigBags", "manufacturerSpecificationsFilename",   messageService.getMessage("err.msg.file.attach.failed", fieldName)));
            }
        }
        if (drawingRemoved) {
            bulk.setDrawingFilename(null);
        } else if (!result.hasErrors() && drawing != null && !drawing.isEmpty()) {
            try {
                bulk.setDrawing(drawing.getBytes());
                bulk.setDrawingContentType(drawing.getContentType());
                bulk.setDrawingFilename(drawing.getOriginalFilename());
            } catch (IOException e) {
                String fieldName = messageService.getMessage("label.container.drawing");
                result.addError(new FieldError("bigBags", "drawingFilename", messageService.getMessage("err.msg.file.attach.failed", fieldName)));
            }
        }
        if (drawingCADRemoved) {
            bulk.setDrawingCAD(null);
        } else if (!result.hasErrors() && drawingCAD != null && !drawingCAD.isEmpty()) {
            try {
                bulk.setDrawingCAD(drawingCAD.getBytes());
                bulk.setDrawingCADContentType(drawingCAD.getContentType());
                bulk.setDrawingCADFilename(drawingCAD.getOriginalFilename());
            } catch (IOException e) {
                String fieldName = messageService.getMessage("label.container.drawing.cad");
                result.addError(new FieldError("bigBags", "drawingCADFilename", messageService.getMessage("err.msg.file.attach.failed", fieldName)));
            }
        }

        if (result.hasErrors() && model != null) {
            prepare(bulk, model);
        }

        return super.edit(bulk, result, redirectAttributes);
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

        BulkService service = getService();
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
    public @ResponseBody AjaxResponse addPhoto(@PathVariable("id") Long bulkID,
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
            BulkService service = getService();
            service.add(bulkID, photo);
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
            BulkService service = getService();
            service.deletePhotoByID(id);
        } catch (Exception e) {
            String error =  messageService.getMessage("err.msg.image.remove.failed");
            return  new AjaxResponse("error", error);
        }
        return new AjaxResponse("success", "");
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit-photos", method = RequestMethod.POST)
    public String editPhotos(@Valid final Bulk bulk,
                             @RequestParam(value = "infoPhotos", required = false) List<MultipartFile> photos,
                             BindingResult result,
                             final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        return super.editPhotos(bulk, result, redirectAttributes);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam(value = "id", required = false) List<Long> ids,
                         final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        return super.remove(ids, redirectAttributes);
    }

    @Override
    protected void validate(Bulk bulk, BindingResult result) {
        if (bulk.getOutletArmatur() == null || bulk.getOutletArmatur().getId() == null) {
            result.addError(new FieldError("bulk", "outletArmatur",
                    messageService.getMessage("err.msg.bulk.outlet.armatur.required")));
        }

        if (bulk.getContainerMaterial() == null || bulk.getContainerMaterial().getId() == null) {
            result.addError(new FieldError("bulk", "containerMaterial",
                    messageService.getMessage("err.msg.bulk.container.material.required")));
        }

        if (bulk.getFrameMaterial() == null || bulk.getFrameMaterial().getId() == null) {
            result.addError(new FieldError("bulk", "frameMaterial",
                    messageService.getMessage("err.msg.bulk.frame.material.required")));
        }

        if (bulk.getHole() == null || bulk.getHole().getId() == null) {
            result.addError(new FieldError("bulk", "hole",
                    messageService.getMessage("err.msg.bulk.hole.required")));
        }
    }

    private BulkService getService() {
        return (BulkService) modelService;
    }

    protected void prepare(Bulk bulk, Model model) throws InternalErrorException {
        prepare(model);
    }

    protected void prepare(Model model) throws InternalErrorException {
        Locale locale = LocaleContextHolder.getLocale();
        model.addAttribute("materials", materialService.getAll(Language.localeOf(locale)));
        model.addAttribute("closures", closureService.getAll(Language.localeOf(locale)));
        model.addAttribute("locations", locationService.getAll(Language.localeOf(locale)));
        model.addAttribute("coatings", coatingService.getAll(Language.localeOf(locale)));
        model.addAttribute("businessunits", businessUnitService.getAll(Language.localeOf(locale)));
        model.addAttribute("couplings", couplingService.getAll(Language.localeOf(locale)));
        model.addAttribute("exzones", exZoneService.getAll(Language.localeOf(locale)));
        model.addAttribute("palettetypes", paletteTypeService.getAll(Language.localeOf(locale)));
        model.addAttribute("companies", companyService.getAll(Language.localeOf(locale)));
    }
}
