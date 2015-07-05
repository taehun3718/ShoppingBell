package com.ktds.high.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter  {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		boolean wasLogin = request.getSession().getAttribute("_MEMBER_") != null;

		if (!wasLogin) {
			response.sendRedirect("http://localhost:8080/ShoppingBell/login");
		}

		return wasLogin;
	}
	
}