package de.mexchange.packagingdb.controller.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Garik on 5/1/16.
 */
public class AdminRoleRequiredInterceptor extends HandlerInterceptorAdapter {

	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response,
							 Object handler) throws Exception {

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			AdminRoleRequired adminRequired = handlerMethod.getMethod().getAnnotation(AdminRoleRequired.class);
			if (adminRequired != null) {
				response.sendRedirect(request.getContextPath());
				return false;
			}
		}
		return true;
	}
}

