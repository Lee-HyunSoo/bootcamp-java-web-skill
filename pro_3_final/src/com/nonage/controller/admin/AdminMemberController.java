package com.nonage.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.service.admin.AdminMemberService;

@WebServlet("/admin/members/*")
public class AdminMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AdminMemberService adminMemberService = new AdminMemberService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path = request.getPathInfo();
		
		if (path.equals("/adminLogin"))
			adminLogin(request, response);
		else if (path.equals("/adminLoginForm"))
			adminLoginForm(request, response);
		else if (path.equals("/adminLogout"))
			adminLogout(request, response);
		else if (path.equals("/adminMemberList"))
			adminMemberList(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/main.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void adminLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/members/adminLogin";
		String msg = "";
		String workerId = request.getParameter("workerId").trim();
		String workerPwd = request.getParameter("workerPwd").trim();
		
		int result = adminMemberService.adminLoginForm(workerId, workerPwd);
		
		if (result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("workerId", workerId);
			response.sendRedirect(request.getContextPath() + "/admin/products/adminProductList");
			return;
		} 
		else if (result == 0) 
			msg = "비밀번호를 확인하세요.";
		else if (result == -1)
			msg = "아이디를 확인하세요.";

		request.setAttribute("message", msg);
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void adminLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			request.setAttribute("message", "");
		}

		response.sendRedirect(request.getContextPath() + "/admin/members/adminLogin");
	}
	
	private void adminMemberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/member/memberList.jsp";
		String key = "";
		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
		}

		request.setAttribute("memberList", adminMemberService.adminMemberList(key));
		request.getRequestDispatcher(url).forward(request, response);
	}

}
