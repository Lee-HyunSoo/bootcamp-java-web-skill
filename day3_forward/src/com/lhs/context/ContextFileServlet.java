package com.lhs.context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cfile")
public class ContextFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		/*
		 * ServletContext의 파일에서 데이터를 읽어오는 기능
		 * 1. getServletContext() 로 ServletContext 접근
		 * 2. getResourceAsStream() 에서 읽어들일 파일 위치를 지정
		 * 3. 파일에서 데이터를 줄단위로 읽기
		 * 4. , 를 기준으로 분리
		 */		
		
		ServletContext context = getServletContext();
		
		InputStream is = context.getResourceAsStream("/WEB-INF/bin/init.txt"); // 외부 파일을 읽어오기 위한 통로 설정
		BufferedReader br = new BufferedReader(new InputStreamReader(is)); // 통로를 통해 넘어온 데이터 읽기
		
		String menu = null;
		String menu_member = null;
		String menu_order = null;
		String menu_goods = null;
		while ((menu = br.readLine()) != null) {
			StringTokenizer tokens = new StringTokenizer(menu, ",");
			menu_member = tokens.nextToken();
			menu_order = tokens.nextToken();
			menu_goods = tokens.nextToken();
		}
		
		out.print("<html><body>");
		out.print("menu_member : " + menu_member + "<br/>");
		out.print("menu_order : " + menu_order + "<br/>");
		out.print("menu_goods : " + menu_goods + "<br/>");
		out.print("<html><body>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
