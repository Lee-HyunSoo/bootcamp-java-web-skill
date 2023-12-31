package com.lhs.url;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
//@WebServlet("/*")
public class TestServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String context = request.getContextPath();
		String url = request.getRequestURL().toString();
		String mapping = request.getServletPath();
		String uri = request.getRequestURI();
		
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Test Servlet3</title>");
		out.print("</head>");
		out.print("<body bgcolor='red'>");
		out.print("<b>TestServlet3입니다.</b><br>");
		out.print("<b>컨텍스트 이름 : " + context + "</b><br>");
		out.print("<b>전체 경로 : " + url + "</b><br>");
		out.print("<b>매핑 이름 : " + mapping + "</b><br>");
		out.print("<b>URI : " + uri + "</b><br>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
