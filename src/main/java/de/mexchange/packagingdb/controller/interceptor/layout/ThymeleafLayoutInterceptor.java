package de.mexchange.packagingdb.controller.interceptor.layout;

import de.mexchange.packagingdb.common.util.StringHelper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Garik on 5/6/16.
 */
public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {

	private static final String CONTEXT_PATH = "contextPath";

	private static final String VIEW_ATTRIBUTE_NAME = "content";

	private static final String PAGE_ATTRIBUTE_TITLE = "pageTitle";

	private static final String DEFAULT_LAYOUT = "layout/default";

	private static final String DEFAULT_PAGE_TITLE_KEY = "title.page.default";

	@Override
	public void postHandle(HttpServletRequest request,
						   HttpServletResponse response,
						   Object handler,
						   ModelAndView modelAndView) throws Exception {

		if (modelAndView == null || !modelAndView.hasView()) {
			return;
		}

		String viewName = modelAndView.getViewName();
		if (isRedirectOrForward(viewName)) {
			return;
		}

		String layoutName = DEFAULT_LAYOUT;
		String pageTitleKey = DEFAULT_PAGE_TITLE_KEY;

		// changing default layout with page title if needed
		if (handler instanceof HandlerMethod) {
			Layout layout = getLayout(handler);
			if (layout != null) {
				if (StringHelper.isNotBlank(layout.value())) {
					layoutName = layout.value();
				}
				if (StringHelper.isNotBlank(layout.titleKey())) {
					pageTitleKey = layout.titleKey();
				}
			}
		}

        modelAndView.addObject(CONTEXT_PATH, request.getContextPath());
		modelAndView.setViewName(layoutName);
		modelAndView.addObject(PAGE_ATTRIBUTE_TITLE, pageTitleKey);

		// setting view content
		modelAndView.addObject(VIEW_ATTRIBUTE_NAME, viewName);
	}

	private boolean isRedirectOrForward(String viewName) {
		return viewName.startsWith("redirect:") || viewName.startsWith("forward:");
	}

	private Layout getLayout(Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Layout layout = handlerMethod.getMethodAnnotation(Layout.class);
		if (layout == null) {
			return handlerMethod.getBeanType().getAnnotation(Layout.class);
		}
		return layout;
	}
}
