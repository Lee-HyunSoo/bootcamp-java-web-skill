package com.lhs.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		description = "테스트용 서블릿", 
		urlPatterns = { "/test" }, 
		initParams = { 
				@WebInitParam(name = "ID", value = "admin", description = "관리자 아이디 초기값")
		})
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("-----> 서블릿 초기화");
	}

	public void destroy() {
		System.out.println("-----> 서블릿 소멸");
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----> 서블릿 서비스 중");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie c1 = new Cookie("ID", "user");
		Cookie c2 = new Cookie("PW", "1234");
		
		response.addCookie(c1);
		response.addCookie(c2);
		
		Cookie[] cooks = request.getCookies();
		System.out.println(cooks[1].getName());
		System.out.println(cooks[1].getValue());
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
