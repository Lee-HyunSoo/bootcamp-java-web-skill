package com.nonage.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.service.admin.AdminQnAService;

@WebServlet("/admin/qnas/*")
public class AdminQnAController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AdminQnAService adminQnAService = new AdminQnAService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path = request.getPathInfo();
		
		if (path.equals("/adminQnAList"))
			adminQnAList(request, response);
		else if (path.equals("/adminQnADetail"))
			adminQnADetail(request, response);
		else if (path.equals("/adminQnARepSave"))
			adminQnARepSave(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void adminQnAList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/qna/qnaList.jsp";

		request.setAttribute("qnaList", adminQnAService.adminQnAList());
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void adminQnADetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/qna/qnaDetail.jsp";
		String qseq = request.getParameter("qseq").trim();
		
		request.setAttribute("qnaVO", adminQnAService.adminQnADetail(qseq));
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void adminQnARepSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qseq = request.getParameter("qseq").trim();
		String reply = request.getParameter("reply").trim();

		adminQnAService.adminQnARepSave(qseq, reply);
		response.sendRedirect(request.getContextPath() + "/admin/products/adminProductList");
	}

}
