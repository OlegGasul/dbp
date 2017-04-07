package de.mexchange.packagingdb.controller.user.object;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.controller.response.AjaxResponse;
import de.mexchange.packagingdb.domain.AggregateState;
import de.mexchange.packagingdb.domain.Material;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.service.AggregateStateService;
import de.mexchange.packagingdb.service.LanguageService;
import de.mexchange.packagingdb.service.MaterialService;
import de.mexchange.packagingdb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/material")
public class MaterialController extends BaseObjectController<Material> {

    private AggregateStateService aggregateStateService;

    @Autowired
    public MaterialController(MaterialService materialService,
                              AggregateStateService aggregateStateService,
                              LanguageService languageService,
                              MessageService messageService) {
        super(materialService, messageService, languageService, Material.class);
        this.aggregateStateService = aggregateStateService;
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Material material, Model model) throws InternalErrorException {
        prepare(material, model);
        return super.addView(material);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid final Material material,
                      BindingResult result,
                      Model model,
                      final RedirectAttributes redirectAttributes) throws InternalErrorException {

        return super.add(material, result, model, redirectAttributes);
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
    public String edit(@PathVariable("id") Long id, Model model, Locale locale)
            throws InternalErrorException, DataNotFoundException {
        model.addAttribute("aggregateStates", aggregateStateService.getAll(Language.localeOf(locale)));
        return super.editView(id, model);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid final Material material,
                       BindingResult result,
                       Model model,
                       final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        return super.edit(material, result, model, redirectAttributes);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam(value = "id", required = false) List<Long> ids,
                         final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        return super.remove(ids, redirectAttributes);
    }

    @Override
    protected void validate(Material material, BindingResult result) {
        AggregateState aggregateState = material.getAggregateState();
        if (aggregateState == null || aggregateState.getId() == null) {
            result.addError(new FieldError("material", "aggregateState",
                    messageService.getMessage("err.msg.material.aggregate.state.required")));
        }
    }

    @Override
    protected void prepare(Material material, Model model) throws InternalErrorException {
        Locale locale = LocaleContextHolder.getLocale();
        model.addAttribute("aggregateStates", aggregateStateService.getAll(Language.localeOf(locale)));
    }
}
