package de.mexchange.packagingdb.controller.user;

import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.domain.News;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Locale;

/**
 * Created by Garik on 4/30/16.
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private NewsService newsService;

	@RequestMapping("/home")
	public String home(Locale locale, Model model) throws InternalErrorException {

		List<News> data = newsService.getAvailableNews(Language.localeOf(locale));

		model.addAttribute("data", data);
		return "user/home";
	}
}
