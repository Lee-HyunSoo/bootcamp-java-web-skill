package com.lhs.context;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cparam")
public class ContextParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		
		/*
		 * 1. getServletContext() 로 ServletContext 객체 접근
		 * 2. getInitParamenter() 의 인자로 각각의 메뉴 이름을 전달 한 후
		 * 3. 메뉴 항목들을 가져와 이를 브라우저로 출력
		 */
		// web.xml 내의 <context-param></context-param> 를 가져오기 위해
		ServletContext context = getServletContext();
		out.print("<html><body>");
		out.print("<table border = 1 cellspacing = 0>");
		out.print("<tr>메뉴이름</tr>");
		out.print("<tr><td>" + context.getInitParameter("menu_member") + "</td></tr>");
		out.print("<tr><td>" + context.getInitParameter("menu_order") + "</td></tr>");
		out.print("<tr><td>" + context.getInitParameter("menu_goods") + "</td></tr>");
		out.print("</table>");
		out.print("<html><body>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
