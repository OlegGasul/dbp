package de.mexchange.packagingdb.controller.user.object;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.CollectionHelper;
import de.mexchange.packagingdb.controller.response.AjaxResponse;
import de.mexchange.packagingdb.domain.Company;
import de.mexchange.packagingdb.domain.CompanyCategory;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.service.*;
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

/**
 * Created by Arthur on 5/23/2016.
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseObjectController<Company>{

    private CompanyCategoryService companyCategoryService;
    private CountryService countryService;

    @Autowired
    public CompanyController(CompanyService companyService,
                             CountryService countryService,
                             CompanyCategoryService companyCategoryService,
                             MessageService messageService,
                             LanguageService languageService) {
        super(companyService, messageService, languageService, Company.class);
        this.companyCategoryService = companyCategoryService;
        this.countryService = countryService;
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Company company, Model model) throws InternalErrorException {
        prepare(company, model);
        return super.addView(company);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid final Company company,
                      BindingResult result,
                      Model model,
                      final RedirectAttributes redirectAttributes) throws InternalErrorException {

        return super.add(company, result, model, redirectAttributes);
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
    public String edit(@PathVariable("id") Long id, Locale locale,
                       Model model) throws InternalErrorException, DataNotFoundException {

        model.addAttribute("countries", countryService.getAll(Language.localeOf(locale)));
        model.addAttribute("categories", companyCategoryService.getAll(Language.localeOf(locale)));
        return super.editView(id, model);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid final Company company,
                       BindingResult result,
                       final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        return super.edit(company, result, redirectAttributes);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam(value = "id", required = false) List<Long> ids,
                         final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

        return super.remove(ids, redirectAttributes);
    }

    @Override
    protected void validate(Company company, BindingResult result) {
        // validating category
        if (CollectionHelper.isEmpty(company.getCompanyCategories())) {
            result.addError(new FieldError("company", "companyCategories",
                   messageService.getMessage("err.field.company.categories.required")));
        } else {
            for (CompanyCategory category: company.getCompanyCategories()) {
                if (category == null || category.getId() == null) {
                    result.addError(new FieldError("company", "companyCategories",
                           messageService.getMessage("err.field.company.categories.invalid")));
                    break;
                }
            }
        }
    }

    @Override
    protected void prepare(Company company, Model model) throws InternalErrorException {
        Locale locale = LocaleContextHolder.getLocale();
        model.addAttribute("countries", countryService.getAll(Language.localeOf(locale)));
        model.addAttribute("categories", companyCategoryService.getAll(Language.localeOf(locale)));
    }
}
