package com.demo.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.demo.entities.Account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AdminInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		if (request.getSession().getAttribute("email") == null) {
//			response.sendRedirect("/account/login");
//			return false;
//		} else {
//			return true;
//		}
		HttpSession session = request.getSession();
		Account loggedInUser = (Account) session.getAttribute("loggedInUser");

		if (loggedInUser == null) {
			response.sendRedirect("/account/login");
			return false;
		} else if (loggedInUser.getRole().getRoleId() != 1) {
			response.sendRedirect("/access-denied");
			return false;
		} else {
			return true;
		}
	}

}
