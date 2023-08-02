package com.lhs.mvc2;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO memberDAO = new MemberDAO();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		String nextPage = null;
		String action = request.getPathInfo();
		
		if (action == null || action.equals("/listMembers.do")) {
			List<MemberVO> members = memberDAO.listMembers();
			request.setAttribute("members", members);
			nextPage = "/mvc2/listMembers.jsp";
		}
		else if (action.equals("/addMember.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			memberDAO.addMember(new MemberVO(id, pwd, name, email));
			nextPage = "/member/listMembers.do";
		}
		else if (action.equals("/memberForm.do")) {
			nextPage = "/mvc2/memberForm.jsp";
		}
		else {
			List<MemberVO> members = memberDAO.listMembers();
			request.setAttribute("members", members);
			nextPage = "/mvc2/listMembers.jsp";
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}
