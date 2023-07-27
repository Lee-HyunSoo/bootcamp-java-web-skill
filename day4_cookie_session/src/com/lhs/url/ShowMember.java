package com.lhs.url;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/show")
public class ShowMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		Boolean isLogin = false;
		HttpSession session = request.getSession(false);
		if (session != null) {
			isLogin = (Boolean)session.getAttribute("isLogin");
			if (isLogin) {
				String id = (String) session.getAttribute("id");
				String pw = (String) session.getAttribute("pw");
				out.print("<html><body>");
				out.print("아이디 : " + id + "<br>");
				out.print("비밀번호 : " + pw + "<br>");
				out.print("</html></body>");
			}
			else {
				response.sendRedirect("login3.html");
			}
		}
		else {
			response.sendRedirect("login3.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
