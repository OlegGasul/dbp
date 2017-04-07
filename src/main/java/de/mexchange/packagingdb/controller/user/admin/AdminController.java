package de.mexchange.packagingdb.controller.user.admin;

import de.mexchange.packagingdb.controller.interceptor.layout.Layout;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Garik on 4/30/16.
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Layout(titleKey = "title.page.administrator")
public class AdminController {

	@RequestMapping("/home")
	public String home() {
		return "user/admin/home";
	}
}
