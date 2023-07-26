package com.lhs.servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/input")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InputServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// name이 모두 같을 때 값 가져오기
//		String[] subs = request.getParameterValues("sub");
//		for (String s : subs)
//			System.out.println(s);
		
		// name이 서로 다를 때 name들 가져오기
//		Enumeration<String> subs = request.getParameterNames();
//		for (; subs.hasMoreElements();)
//			System.out.println(subs.nextElement());
		
//		while (subs.hasMoreElements()) {
//			System.out.println(subs.nextElement());
//		}

		// name이 서로 다를 때 name, value 가져오기
		Map<String, String> paramMap = new HashMap<>();
		request.getParameterNames().asIterator()
			.forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
		
		for (Map.Entry<String, String> entry : paramMap.entrySet())
			System.out.println("key : " + entry.getKey() + " value : " + entry.getValue());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
