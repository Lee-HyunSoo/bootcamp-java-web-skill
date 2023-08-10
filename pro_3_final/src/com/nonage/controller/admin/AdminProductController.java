package com.nonage.controller.admin;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.service.admin.AdminProductService;
import com.nonage.vo.MemberVO;
import com.nonage.vo.ProductVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/admin/products/*")
public class AdminProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminProductService adminProductService = new AdminProductService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String path = request.getPathInfo();

		if (path.equals("/adminProductList"))
			adminProductList(request, response);
		else if(path.equals("/adminProductDetail"))
			adminProductDetail(request, response);
		else if(path.equals("/adminProductUpdateForm"))
			adminProductUpdateForm(request, response);
		else if(path.equals("/adminProductUpdate"))
			adminProductUpdate(request, response);
		else if(path.equals("/adminProductWriteForm"))
			adminProductWriteForm(request, response);
		else if(path.equals("/adminProductWrite")) {
			adminProductWrite(request, response);	
		}else if(path.equals("/adminProductDelete")) {
			adminProductDelete(request,response);
		}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println(request.getParameter("key") + "<====>>>>");
		System.out.println(request.getParameter("useyn"));
		System.out.println(request.getParameter("bestyn"));
		doGet(request, response);
	}
	
	private void adminProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/product/productList.jsp";
		 request.setCharacterEncoding("UTF-8");
		String key = request.getParameter("key");
		String tpage = request.getParameter("tpage");

		if (key == null)
			key = "";

		if (tpage == null || tpage.equals(""))
			tpage = "1"; // 현재 페이지 (default 1)
	
		Map<String, Object> model = adminProductService.adminProductList(key, tpage, request.getContextPath());
		
		request.setAttribute("key", key);
		request.setAttribute("tpage", tpage);
		request.setAttribute("productList", model.get("productList"));
		request.setAttribute("productListSize", model.get("productListSize"));
		request.setAttribute("paging", model.get("paging"));
	
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void adminProductDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/product/productDetail.jsp";
		String pseq = request.getParameter("pseq").trim();
		
		ProductVO productVO = adminProductService.adminProductDetail(pseq);
		request.setAttribute("productVO", productVO);

		// 상품 리스트(product_list.jsp) 페이지에서 쿼리 스트링으로 넘겨준 현재 페이지를 얻어온다.
		String tpage = "1";
		if (request.getParameter("tpage") != null) 
			tpage = request.getParameter("tpage");

		String kindList[] = { "0", "Heels", "Boots", "Sandals", "Slipers", "Sneakers" };
		request.setAttribute("tpage", tpage);
		int index = Integer.parseInt(productVO.getKind().trim());
		request.setAttribute("kind", kindList[index]);

		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void adminProductUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/product/productUpdate.jsp";
		String pseq = request.getParameter("pseq").trim();

		// 상품 리스트(product_list.jsp) 페이지에서 쿼리 스트링으로 넘겨준 현재 페이지를 얻어온다.
		String tpage = "1";
		if (request.getParameter("tpage") != null) {
			tpage = request.getParameter("tpage");
		}
		String kindList[] = { "Heels", "Boots", "Sandals", "Slipers", "Sneakers" };
		request.setAttribute("productVO", adminProductService.adminProductUpdateForm(pseq));
		request.setAttribute("tpage", tpage);
		request.setAttribute("kindList", kindList);

		request.getRequestDispatcher(url).forward(request, response);
	}

	private void adminProductUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		int sizeLimit = 5 * 1024 * 1024;
		String savePath = "product_images";
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);

		MultipartRequest multi = new MultipartRequest(request, // 1. 요청 객체
				uploadFilePath, // 2. 업로드될 파일이 저장될 파일 경로명
				sizeLimit, // 3. 업로드될 파일의 최대 크기(5Mb)
				"UTF-8", // 4. 인코딩 타입 지정
				new DefaultFileRenamePolicy() // 5. 덮어쓰기를 방지 위한 부분
		); // 이 시점을 기해 파일은 이미 저장이 되었다

		adminProductService.adminProductUpdate(multi);
		response.sendRedirect(request.getContextPath() + "/admin/products/adminProductList");
	}
	
	private void adminProductWriteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/product/productWrite.jsp";
		String kindList[] = { "Heels", "Boots", "Sandals", "Slipers", "Sneakers" };
		request.setAttribute("kindList", kindList);
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void adminProductWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int sizeLimit = 5 * 1024 * 1024;
		String savePath = "product_images";
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
		// adminProductWrite(request, response);	
		
		MultipartRequest multi = new MultipartRequest(request, // 1. 요청 객체
				uploadFilePath, // 2. 업로드될 파일이 저장될 파일 경로명
				sizeLimit, // 3. 업로드될 파일의 최대 크기(5Mb)
				"UTF-8", // 4. 인코딩 타입 지정
				new DefaultFileRenamePolicy() // 5. 덮어쓰기를 방지 위한 부분
		); // 이 시점을 기해 파일은 이미 저장이 되었다
		
		adminProductService.adminProductWrite(multi);

		response.sendRedirect(request.getContextPath() + "/admin/products/adminProductList");
	}
	
	private void adminProductDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pseq = request.getParameter("pseq");
		System.out.println(pseq);
		String url =  request.getContextPath()+ "/admin/products/adminProductList";
		adminProductService.adminProductDelete(Integer.parseInt(pseq));
		response.sendRedirect(url);
	    
	}
	
	
}
