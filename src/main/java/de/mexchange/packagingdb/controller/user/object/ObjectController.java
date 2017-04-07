package de.mexchange.packagingdb.controller.user.object;

import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.common.exception.NoServiceFoundException;
import de.mexchange.packagingdb.domain.BusinessUnit;
import de.mexchange.packagingdb.domain.Coating;
import de.mexchange.packagingdb.domain.Cubic;
import de.mexchange.packagingdb.service.ModelService;
import de.mexchange.packagingdb.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Garik on 5/8/16.
 */
@Controller
@RequestMapping("/objects")
public class ObjectController {

	private ServiceFactory serviceFactory;

	@Autowired
	public ObjectController(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}

	@PreAuthorize("hasRole('ROLE_WRITER') or hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addForm(Model model, HttpSession session) {

		if (session.getAttribute("message") != null) {
			model.addAttribute("message", session.getAttribute("message"));
			session.removeAttribute("message");
		}

		populate(model, new HashMap<String, Object>() {{
			put("selectedObject", "-1");
		}});

		return "user/objects/new-object";
	}

	@RequestMapping(value = "/addCoating", method = RequestMethod.POST)
	public String add(@Valid final Coating coating,
					  BindingResult result,
					  Model model,
					  HttpSession session) throws NoServiceFoundException, InternalErrorException {

		if (result.hasErrors()) {
			model.addAttribute("selectedObject", "coating-form");

			populate(model, new HashMap<String, Object>() {{
				put("selectedObject", "coating-form");
				put("coating", coating);
			}});

			return "user/objects/new-object";
		}

		ModelService service = serviceFactory.get(Coating.class);
		service.add(coating);

		session.setAttribute("message", "Coating object added");
		return "redirect:add";
	}

	@RequestMapping(value = "/addBusinessUnit", method = RequestMethod.POST)
	public String add(@Valid final BusinessUnit businessUnit,
					  BindingResult result,
					  Model model,
					  HttpSession session) throws NoServiceFoundException, InternalErrorException {

		if (result.hasErrors()) {
			populate(model, new HashMap<String, Object>() {{
				put("selectedObject", "businessUnit-form");
				put("businessUnit", businessUnit);
			}});

			return "user/objects/new-object";
		}

		ModelService service = serviceFactory.get(BusinessUnit.class);
		service.add(businessUnit);

		session.setAttribute("message", "Business Unit object added");
		return "redirect:add";
	}

	@RequestMapping(value = "/addCubic", method = RequestMethod.POST)
	public String add(@Valid final Cubic cubic,
					  BindingResult result,
                      @RequestParam(value = "photos", required = false) List<MultipartFile> photos,
					  Model model,
					  HttpSession session) throws NoServiceFoundException, InternalErrorException {

		if (result.hasErrors()) {
			populate(model, new HashMap<String, Object>() {{
				put("selectedObject", "cubic-form");
				put("cubic", cubic);
			}});

			return "user/objects/new-object";
		}

		session.setAttribute("message", "Cubic object added");
		return "redirect:add";
	}

	private void populate(Model model, Map<String, Object> initialized) {

		if (initialized != null) {
			model.addAllAttributes(initialized);
		}

		if (!model.containsAttribute("coating")) {
			model.addAttribute("coating", new Coating());
		}
		if (!model.containsAttribute("businessUnit")) {
			model.addAttribute("businessUnit", new BusinessUnit());
		}
		if (!model.containsAttribute("cubic")) {
			model.addAttribute("cubic", new Cubic());
		}
	}
}
