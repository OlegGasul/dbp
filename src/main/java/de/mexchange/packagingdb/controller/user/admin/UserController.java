package de.mexchange.packagingdb.controller.user.admin;

import de.mexchange.packagingdb.common.exception.DataNotFoundException;
import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.util.CollectionHelper;
import de.mexchange.packagingdb.common.util.StringHelper;
import de.mexchange.packagingdb.controller.interceptor.layout.Layout;
import de.mexchange.packagingdb.controller.response.AjaxResponse;
import de.mexchange.packagingdb.controller.response.ListAjaxResponse;
import de.mexchange.packagingdb.controller.user.object.BaseObjectController;
import de.mexchange.packagingdb.domain.User;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.domain.lcp.UserRole;
import de.mexchange.packagingdb.domain.lcp.UserStatus;
import de.mexchange.packagingdb.service.CountryService;
import de.mexchange.packagingdb.service.LanguageService;
import de.mexchange.packagingdb.service.MessageService;
import de.mexchange.packagingdb.service.UserService;
import de.mexchange.packagingdb.service.notification.util.EmailNotificationEvent;
import de.mexchange.packagingdb.service.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Garik on 5/3/16.
 */
@Controller("adminUserController")
@RequestMapping("/admin/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Layout(titleKey = "title.page.user.management")
public class UserController {

	private UserService userService;

	private CountryService countryService;

	private MessageService messageService;

	@Autowired
	public UserController(UserService userService, CountryService countryService, MessageService messageService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.countryService = countryService;
		this.messageService = messageService;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUserForm(User user, Model model, Locale locale) throws InternalErrorException {
		prepare(user, model);
		return "user/admin/new-user";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid User user,
					  BindingResult result,
                      Model model,
					  HttpServletRequest httpRequest,
					  @AuthenticationPrincipal User admin) throws InternalErrorException {

		if (result.hasErrors()) {
			prepare(user, model);
			return "user/admin/new-user";
		}

		String url = PasswordUtil.preparePasswordChangeUrl(httpRequest, EmailNotificationEvent.ACTIVATE_ACCOUNT);
		userService.add(user, url);

		return "redirect:list";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model)
			throws InternalErrorException, DataNotFoundException {
		User user = userService.getByID(id);
		prepare(user, model);
		return "user/admin/edit-user";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@Valid final User user,
					   BindingResult result,
					   Model model,
					   final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

		// doing additional validation
		validate(user, result);

		if (result.hasErrors()) {
			if (model != null) {
				prepare(user, model);
			}
			return "user/admin/edit-user";
		}

		userService.edit(user);

		String message = messageService.getMessage("msg.user.object.edited");
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String userList(Model model) throws InternalErrorException {
		long listCount = userService.getListCount();
		model.addAttribute("dataCount", listCount);
		return "user/admin/user-list";
	}

	@RequestMapping(value = "/list-items", method = RequestMethod.GET)
	public @ResponseBody
	AjaxResponse listItems(@RequestParam(value = "page", required = false) int page,
						   @RequestParam(value = "count", required = false, defaultValue = "10") int count,
						   @RequestParam(value = "role", required = false) String[] role,
						   @RequestParam(value = "status", required = false) String[] status,
						   @RequestParam(value = "search", required = false) String search
						   ) {
		try {
			long listCount = userService.getListCount(role, status, search);
			if (page * 10 > listCount) {
				page = (int) (listCount / 10);
				if (listCount % 10 > 0) {
					page += 1;
				}
			}

			List<User> list = userService.getList(page, count, role, status, search);
			return new ListAjaxResponse("success", list, listCount);
		} catch (Exception e) {
			return new AjaxResponse("error", "Failed to retrieve list items");
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam(value = "id", required = false) List<Long> ids,
						 final RedirectAttributes redirectAttributes) throws DataNotFoundException, InternalErrorException {

		if (CollectionHelper.isEmpty(ids)) {
			redirectAttributes.addFlashAttribute("message", "No item selected to be removed");
			return "redirect:list";
		}

		userService.deleteByIDs(ids);
		String message;
		if (ids.size() > 1) {
			message = messageService.getMessage("msg.user.objects.removed", ids.size());
		} else {
			message = messageService.getMessage("msg.user.object.removed");
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:list";
	}

	protected void validate(User user, BindingResult result) {}

	protected void prepare(User user, Model model) throws InternalErrorException {
		Locale locale = LocaleContextHolder.getLocale();
		model.addAttribute("user", user);
		model.addAttribute("countries", countryService.getAll(Language.localeOf(locale)));
	}
}
