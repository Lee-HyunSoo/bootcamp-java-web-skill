package com.lhs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "회원정보(가입 / 삭제 / 목록)", urlPatterns = "/member2")
public class MemberServlet2 extends HttpServlet {
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
		
		MemberDAO memberDAO = new MemberDAO();
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");
		
		if (command != null && command.equals("addMember")) {
			MemberVO memberVO = new MemberVO();
			memberVO.setId(request.getParameter("id"));
			memberVO.setPwd(request.getParameter("pwd"));
			memberVO.setName(request.getParameter("name"));
			memberVO.setEmail(request.getParameter("email"));
			memberDAO.addMember(memberVO);
		}
		else if (command != null && command.equals("delMember")) {
			String id = request.getParameter("id");
			memberDAO.delMember(id);
		}
		else if (command != null && command.equals("modifyMember")) {
			MemberVO memberVO = new MemberVO();
			memberVO.setId(request.getParameter("id"));
			memberVO.setPwd(request.getParameter("pwd"));
			memberVO.setName(request.getParameter("name"));
			memberVO.setEmail(request.getParameter("email"));
			System.out.println(memberVO + " modify");
			memberDAO.modifyMember(memberVO);
		}
		
		List<MemberVO> members = memberDAO.findAll();
		out.print("<html><body>");
		out.print("<table border = 1>");
		out.print("<tr align='center' bgcolor='lightgreen'>"
				+ "<td>아이디</td>"
				+ "<td>비밀번호</td>"
				+ "<td>이름</td>"
				+ "<td>이메일</td>"
				+ "<td>가입일</td>"
				+ "<td>삭제</td>"
				+ "<td>수정</td>"
				+ "</tr>");		
		for(MemberVO m : members) {
			out.print("<tr>"
					+ "<td>" + m.getId() + "</td>"
					+ "<td>" + m.getPwd() + "</td>"
					+ "<td>" + m.getName() + "</td>"
					+ "<td>" + m.getEmail() + "</td>"
					+ "<td>" + m.getJoinDate() + "</td>"
					+ "<td><a href='/day2_jdbc/member2?command=delMember&id=" + m.getId() + "'>삭제</a></td>"
					+ "<td><a href='/day2_jdbc/memberModify.jsp?id=" + m.getId() +"'>수정</a></td>"
					+ "</tr>");
		}
		out.print("</table>");
		out.print("</body></html>");
		out.print("<a href='/day2_jdbc/memberForm.html'>새 회원 등록하기</a>");
	}
	
}
