package com.lhs.mvc3;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhs.mvc3.MemberVO;


//@WebServlet("*.do" )
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
//		String action = request.getPathInfo();
		String uri = request.getRequestURI();
		int index = uri.lastIndexOf("/");
		String action = uri.substring(index);
		
		if (action == null || action.equals("/listMembers.do")) {
			List<MemberVO> members = memberDAO.listMembers();
			request.setAttribute("members", members);
			nextPage = "/mvc3/listMembers.jsp";
		}
		else if (action.equals("/modMemberForm.do")) {
			String id = request.getParameter("id");
			MemberVO member = memberDAO.findMember(id);
			request.setAttribute("member", member);
			nextPage = "/mvc3/modMemberForm.jsp";
		}
		else if (action.equals("/modMember.do")) {
			
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			memberDAO.modMember(new MemberVO(id, pwd, name, email));
			request.setAttribute("msg", "modified");
			nextPage = "/member/listMembers.do";
		}
		else if (action.equals("/delMember.do")) {
			String id = request.getParameter("id");
			memberDAO.delMember(id);
			request.setAttribute("msg", "deleted");
			nextPage = "/member/listMembers.do";
		}
		else {
			List<MemberVO> members = memberDAO.listMembers();
			request.setAttribute("members", members);
			nextPage = "/mvc3/listMembers.jsp";
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}
