package com.lhs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "loadConfig", urlPatterns = "/loadConfig2")
public class LoadAppConfigWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("LoadAppConfigWeb의 init 메서드 호출");
		context = config.getServletContext();
		
		context.setAttribute("menu_member", context.getInitParameter("menu_member"));
		context.setAttribute("menu_order", context.getInitParameter("menu_order"));
		context.setAttribute("menu_goods", context.getInitParameter("menu_goods"));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<html><body>");
		out.print("<table border = 1 cellspacing = 0>");
		out.print("<tr>메뉴이름</tr>");
		out.print("<tr><td>" + (String)context.getAttribute("menu_member") + "</td></tr>");
		out.print("<tr><td>" + (String)context.getAttribute("menu_order") + "</td></tr>");
		out.print("<tr><td>" + (String)context.getAttribute("menu_goods") + "</td></tr>");
		out.print("</table>");
		out.print("</html></body>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
