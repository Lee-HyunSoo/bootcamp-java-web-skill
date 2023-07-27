package com.lhs.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sess5")
public class SessionTest5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if (session.isNew()) {
			if (id != null) {
				session.setAttribute("id", id);
				String url = response.encodeURL("sess5");
				out.print("<a href=" + url + ">로그인 상태 확인</a>");
			}
			else {
				out.print("<a href='login2.html'>다시 로그인하세요!!!</a>");
				session.invalidate();
			}
		}
		else {
			id = (String) session.getAttribute("id");
			if (id != null && id.length() != 0) {
				out.print("안녕하세요 " + id + "님!!!");
			}
			else {
				out.print("<a href='login2.html'>다시 로그인하세요!!!</a>");
				session.invalidate();
			}
		}
	}
}
