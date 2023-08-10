package com.nonage.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.service.admin.AdminOrderService;

@WebServlet("/admin/orders/*")
public class AdminOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final AdminOrderService adminOrderService = new AdminOrderService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path = request.getPathInfo();
			
		if (path.equals("/adminOrderList"))
			adminOrderList(request, response);
		else if (path.equals("/adminOrderSave"))
			adminOrderSave(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void adminOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/order/orderList.jsp";
		String key = "";
		
		if (request.getParameter("key") != null) 
			key = request.getParameter("key");
	
		request.setAttribute("orderList", adminOrderService.adminOrderList(key));
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void adminOrderSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] resultArr = request.getParameterValues("result");
		adminOrderService.adminOrderSave(resultArr);
		
		response.sendRedirect(request.getContextPath() + "/admin/orders/adminOrderList");
		return;
	}

}
