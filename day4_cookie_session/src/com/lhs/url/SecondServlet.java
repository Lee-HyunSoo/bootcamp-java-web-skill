package com.lhs.url;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String address = request.getParameter("address");
		
		if (id != null && id.length() != 0) {
			out.print("<html><body>");
			out.print("이미 로그인 상태입니다!<br/><br/>");
			out.print("첫 번째 서블릿에서 넘겨준 아이디 : " + id + "<br/>");
			out.print("첫 번째 서블릿에서 넘겨준 비밀번호 : " + pw + "<br/>");
			out.print("첫 번째 서블릿에서 넘겨준 주소 : " + address + "<br/>");
			out.print("</html></body>");
			out.close();
		}
		else {
			out.print("로그인하지 않았습니다.<br/><br/>");
			out.print("다시 로그인하세요!!<br/>");
			out.print("<a href='/day4_cookie_session/login.html'>로그인 창으로 이동하기</a>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
