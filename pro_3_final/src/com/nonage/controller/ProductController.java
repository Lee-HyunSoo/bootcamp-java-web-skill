package com.nonage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.dao.ProductDAO;
import com.nonage.service.ProductService;
import com.nonage.vo.ProductVO;


/**
 * 
 * @author 한신우
 * indexAction,상품상세보기,메뉴이동
 *
 */

@WebServlet("/products/*")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final ProductService service = ProductService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	
	String action = request.getPathInfo();
	System.out.println("action -->"+action);
	if(action == null) {
		indexAction(request,response);
	}else if(action.equals("/productDetail")) {
		getProduct(request,response);
	}else if(action.equals("/catagory")) {
		choiceMenu(request,response);
	}
	
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

	
	public void indexAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/index.jsp";


		ArrayList<ProductVO> newProductList = service.listNewProduct();
		ArrayList<ProductVO> bestProductList = service.listBestProduct();
		request.setAttribute("newProductList", newProductList);
		request.setAttribute("bestProductList", bestProductList);

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	public void getProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/product/productDetail.jsp";

		String pseq = request.getParameter("pseq").trim();

		ProductVO productVO = service.getProduct(pseq);

		request.setAttribute("productVO", productVO);

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	public void choiceMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/product/productKind.jsp";

		String kind = request.getParameter("kind").trim();

		
		ArrayList<ProductVO> productKindList = service.listKindProduct(kind);

		request.setAttribute("productKindList", productKindList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	
}
