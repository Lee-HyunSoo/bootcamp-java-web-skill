package com.lhs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String hp = request.getParameter("hp");
		
		out.print("<html><body>");
		out.print("아이디 : " + id + "<br/>");
		out.print("비밀번호 : " + pw + "<br/>");
		out.print("주소 : " + address + "<br/>");
		out.print("이메일 : " + email + "<br/>");
		out.print("전화번호 : " + hp + "<br/>");
		out.print("<a href='/day4_cookie_session/second?id=" + id + "&pw=" + pw + "&address=" + address + "'>두 번째 서블릿으로 보내기</a>");
		out.print("</html></body>");
		out.close();
	}
	
	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}

}
