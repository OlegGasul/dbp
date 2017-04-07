package de.mexchange.packagingdb.controller.user.object;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.controller.response.AjaxResponse;
import de.mexchange.packagingdb.domain.PackagingInstruction;
import de.mexchange.packagingdb.service.LanguageService;
import de.mexchange.packagingdb.service.MessageService;
import de.mexchange.packagingdb.service.VerpInstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Arthur on 5/23/2016.
 */
@Controller
@RequestMapping("/packaginginstruction")
public class PackagingInstructionController extends BaseObjectController<PackagingInstruction> {

    @Autowired
    public PackagingInstructionController(VerpInstructionService verpInstructionService , MessageService messageService, LanguageService languageService) {
        super(verpInstructionService, messageService, languageService, PackagingInstruction.class);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(PackagingInstruction packagingInstruction, Model model) {
        model.addAttribute("packagingInstruction", packagingInstruction);
        return super.addView(packagingInstruction);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid final PackagingInstruction packagingInstruction,
                      BindingResult result,
                      final RedirectAttributes redirectAttributes) throws InternalErrorException {

        return super.add(packagingInstruction, result, redirectAttributes);
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
    public String edit(@PathVariable("id") Long id, Model model) throws InternalErrorException, DataNotFoundException {
        return super.editView(id, model);
    }

    @PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid final PackagingInstruction packagingInstruction,
                       BindingResult result,
                       final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {
        return super.edit(packagingInstruction, result, redirectAttributes);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam(value = "id", required = false) List<Long> ids,
                         final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {
        return super.remove(ids, redirectAttributes);
    }
}
