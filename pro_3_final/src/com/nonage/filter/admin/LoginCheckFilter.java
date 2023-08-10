package com.nonage.filter.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/admin/products/*", "/admin/orders/*", "/admin/qnas/*" })
public class LoginCheckFilter implements Filter {

	public LoginCheckFilter() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setContentType("text/html; charset=utf-8");

		try {
			HttpSession session = httpRequest.getSession(false);
			
			if (session == null || session.getAttribute("workerId") == null) {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/admin/members/adminLogin");
				return;
			}

			chain.doFilter(request, response);

		} catch (Exception e) {
			throw e;
		} finally {
			System.out.println("Admin Login Filter 종료");
		}
	}

}
