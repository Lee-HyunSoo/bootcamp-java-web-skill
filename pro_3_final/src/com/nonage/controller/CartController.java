package com.nonage.controller;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.CartDAO;
import com.nonage.service.CartService;
import com.nonage.vo.CartVO;
import com.nonage.vo.MemberVO;

/**
 * 
 * @author 한신우
 * 장바구니 리스트,장바구니 넣기, 장바구니 삭제기능
 *
 */


@WebServlet("/cart/*")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

  private CartService service =	CartService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getPathInfo();
		System.out.println("액션===> "+action);
		if(action.equals("/cart_list")) {
			cartList(request,response);
		}else if(action.equals("/cart_insert")) {
			cartInsert(request,response);
		}else if(action.equals("/cart_delete")) {
			cartDelete(request,response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

	
	
	private void cartList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/member/login.jsp";
		HttpSession session = request.getSession();
	
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser != null) {
			List<CartVO> cart  = service.listCart(loginUser.getId());
			request.setAttribute("cartList", cart);
			url = "/mypage/cartList.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}
	
	public void cartDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/mypage/cartList.jsp";

		String[] cseqArr = request.getParameterValues("cseq");

		for (String cseq : cseqArr) {
		
		service.deleteCart(Integer.parseInt(cseq));
		}
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser != null) {
			List<CartVO> cart  = service.listCart(loginUser.getId());
			request.setAttribute("cartList", cart);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	
	public void cartInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url  = "/mypage/cartList.jsp";
		 
		HttpSession session = request.getSession();

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			url = "/member/login.jsp";
		} else {
			CartVO cartVO = new CartVO();
			cartVO.setId(loginUser.getId());
			cartVO.setPseq(Integer.parseInt(request.getParameter("pseq")));
			cartVO.setQuantity(Integer.parseInt(request.getParameter("quantity")));

			
			service.insertCart(cartVO);
			List<CartVO> cart  = service.listCart(loginUser.getId());
			request.setAttribute("cartList", cart);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
}
