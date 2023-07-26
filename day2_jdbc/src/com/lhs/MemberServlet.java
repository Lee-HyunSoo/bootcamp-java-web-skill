package com.lhs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); // HttpMessage header
		PrintWriter out = response.getWriter(); // jsp 내의 PrintWriter와 다르다
		
		MemberDAO memberDAO = new MemberDAO();
		List<MemberVO> members = memberDAO.findAll();
		
		out.print("<html><body>");
		out.print("<table border = 1>");
		out.print("<tr align='center' bgcolor='lightgreen'>"
				+ "<td>아이디</td>"
				+ "<td>비밀번호</td>"
				+ "<td>이름</td>"
				+ "<td>이메일</td>"
				+ "<td>가입일</td>"
				+ "</tr>");		
		for(MemberVO m : members) {
			out.print("<tr>"
					+ "<td>" + m.getId() + "</td>"
					+ "<td>" + m.getPwd() + "</td>"
					+ "<td>" + m.getName() + "</td>"
					+ "<td>" + m.getEmail() + "</td>"
					+ "<td>" + m.getJoinDate() + "</td>"
					+ "</tr>");
		}
		out.print("</table>");
		out.print("</body></html>");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
