package de.mexchange.packagingdb.controller;

import de.mexchange.packagingdb.controller.interceptor.layout.Layout;
import de.mexchange.packagingdb.domain.User;
import de.mexchange.packagingdb.domain.lcp.UserRole;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Garik on 4/30/16.
 */
@Controller
@Layout(titleKey = "title.page.login")
public class AuthenticationController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(Model model,
							   @AuthenticationPrincipal User user,
							   @RequestParam(required = false) String error,
							   @RequestHeader(value = "referer", required = false, defaultValue = "/home") final String referer) {

		// goes to previous or home page in case of authenticated user
		if (user != null) {
			if (referer.equals("login")) {
				return "redirect:home";
			}
			return "redirect:" + referer;
		}

		model.addAttribute("error", error);
		return "login";
	}

	@PreAuthorize("hasRole('ROLE_READER') or hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
	@RequestMapping("/home")
	public String home(@AuthenticationPrincipal User user) {
		/*if (user.getRole() == UserRole.ROLE_ADMIN) {
			return "redirect:admin/home";
		}*/
		return "redirect:user/home";
	}
}
