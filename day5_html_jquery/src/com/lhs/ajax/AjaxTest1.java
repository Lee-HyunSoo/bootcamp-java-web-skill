package com.lhs.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 제이쿼리 Ajax 기능
 * 클라이언트 측에서의 작업과는 상관 없이 비동기적으로 서버와 작업을 수행할 때 Ajax 기능을 사용
 * Ajax란 Asynchronous JavaScript (비동기 자바스크립트) + XML의 의미로 자바스크립트를 사용한 비동기 통신
 * 즉, 클라이언트와 서버 간의 XML이나 JSON 데이터를 주고받는 기술을 의미
 * 
 * Ajax는 페이지 이동 없이 데이터 처리가 가능하며, 서버의 처리를 기다리지 않고 비동기 요청이 가능하다는 특징
 */
@WebServlet("/ajaxTest1")
public class AjaxTest1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandler(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandler(request, response);
	}

	private void doHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String param = (String)request.getParameter("param");
		System.out.println("param = " + param);
		PrintWriter writer = response.getWriter();
		writer.print("안녕하세요! 서버입니다.");
	}
}
