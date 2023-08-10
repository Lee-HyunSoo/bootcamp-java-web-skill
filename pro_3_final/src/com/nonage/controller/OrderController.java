package com.nonage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.service.CartService;
import com.nonage.service.OrderService;
import com.nonage.vo.CartVO;
import com.nonage.vo.MemberVO;
import com.nonage.vo.OrderVO;


/**
 * 
 * @author 한신우
 * 주문하기,주문내역
 *
 */


@WebServlet("/order/*")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	  private OrderService service =	OrderService.getInstance();
	  private CartService service2 =	CartService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getPathInfo();
		System.out.println(action);
		if(action.equals("/mypage")) {
			selectSeqOrderIng(request, response);
		}else if(action.equals("/orderAll")) {
		OrderAll(request, response);
		}else if(action.equals("/orderDetail")) {
			OrderDetail(request, response);
		}else if(action.equals("/insertOrder")) {
			insertOrder(request, response);
		}else if(action.equals("/orderList")) {
			OrderList(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

	
	private void selectSeqOrderIng(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/mypage/mypage.jsp";

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			 url = "/member/login.jsp";
		} else {
		
			ArrayList<Integer> oseqList = service.selectSeqOrderIng(loginUser.getId());

			ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();

			for (int oseq : oseqList) {
				ArrayList<OrderVO> orderListIng = service.listOrderById(loginUser.getId(), "1", oseq);

				OrderVO orderVO = orderListIng.get(0);
				orderVO.setPname(orderVO.getPname() + " 외 " + (orderListIng.size()-1) + "건");

				int totalPrice = 0;
				for (OrderVO ovo : orderListIng) {
					totalPrice += ovo.getPrice2() * ovo.getQuantity();
				}
				orderVO.setPrice2(totalPrice);
				orderList.add(orderVO);
			}
			request.setAttribute("title", "진행 중인 주문 내역");
			request.setAttribute("orderList", orderList);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	private void OrderAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/mypage/mypage.jsp";

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			 url = "/member/login.jsp";
		} else {
		
			ArrayList<Integer> oseqList = service.selectSeqOrderIng(loginUser.getId());
			ArrayList<Integer> oseqList2 = service.selectSeqOrderIng2(loginUser.getId());
			
			ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
			ArrayList<OrderVO> orderList2 = new ArrayList<OrderVO>();

		
			
			for (int oseq : oseqList) {
				ArrayList<OrderVO> orderListIng = service.listOrderById(loginUser.getId(), "%", oseq);

				OrderVO orderVO = orderListIng.get(0);
				orderVO.setPname(orderVO.getPname() + " 외 " + (orderListIng.size()-1) + "건");
		
				int totalPrice = 0;
				for (OrderVO ovo : orderListIng) {
					totalPrice += ovo.getPrice2() * ovo.getQuantity();
				}
				orderVO.setPrice2(totalPrice);
			
				orderList.add(orderVO);
			}
			
			for (int oseq : oseqList2) {
				ArrayList<OrderVO> orderListIng = service.listOrderById(loginUser.getId(), "%", oseq);

				OrderVO orderVO = orderListIng.get(0);
				orderVO.setPname(orderVO.getPname() + " 외 " + (orderListIng.size()-1) + "건");
		
				int totalPrice = 0;
				for (OrderVO ovo : orderListIng) {
					totalPrice += ovo.getPrice2() * ovo.getQuantity();
				}
				orderVO.setPrice2(totalPrice);
				orderVO.setGubun(1);
				orderList2.add(orderVO);
			}
			
		
			List<OrderVO> orderall = new ArrayList<>();
			orderall.addAll(orderList);
			orderall.addAll(orderList2);
			
		
			    int itemsPerPage = 10; // 페이지당 아이템 수
			    int totalItems = orderall.size(); // 전체 주문 아이템 수
			    
			    int tpage  = Integer.parseInt(request.getParameter("tpage"));
			    
			    if ( tpage== 0 ) {
			            tpage =1;
			        } 
			    
			    int startIdx = (tpage - 1) * itemsPerPage;
			    int endIdx = Math.min(startIdx + itemsPerPage, totalItems);
			    
			    List<OrderVO> paginatedOrderList = orderall.subList(startIdx, endIdx);
		
			request.setAttribute("title", "총 주문 내역");
			request.setAttribute("currentPage", tpage);
		    request.setAttribute("itemsPerPage", itemsPerPage);
		    request.setAttribute("totalItems", totalItems);
		    request.setAttribute("totalPages", (int) Math.ceil((double) totalItems / itemsPerPage));
		    request.setAttribute("orderList", paginatedOrderList);
//			request.setAttribute("orderList", orderall);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	public void OrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/mypage/orderDetail.jsp";

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			 url = "/member/login.jsp";
		} else {
			int oseq = Integer.parseInt(request.getParameter("oseq"));
			ArrayList<OrderVO> orderList = service.listOrderById(loginUser.getId(), "%", oseq);

			int totalPrice = 0;
			for (OrderVO ovo : orderList) {
				totalPrice += ovo.getPrice2() * ovo.getQuantity();
			}
			request.setAttribute("orderDetail", orderList.get(0));
			request.setAttribute("orderList", orderList);
			request.setAttribute("totalPrice", totalPrice);
		
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	public void insertOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";

		HttpSession session = request.getSession();
	
		int maxOseq = 0;
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			url =  request.getContextPath()+"/member/login.jsp";
		} else {
			if(request.getParameterValues("cseq") != null) {
			String[] values= request.getParameterValues("cseq");
			
			List<CartVO> cartList = service2.listCart(loginUser.getId());
			List<CartVO> orderin = new ArrayList<>();
			for(CartVO cart : cartList) {
				for(String value : values) {
					if(cart.getCseq() == Integer.parseInt(value)) {
						orderin.add(cart);					
					}
				}
			}
			maxOseq = service.insertOrder(orderin, loginUser.getId());
			}else {
				CartVO cartVO = new CartVO();
				cartVO.setId(loginUser.getId());
				cartVO.setPseq(Integer.parseInt(request.getParameter("pseq")));
				cartVO.setQuantity(Integer.parseInt(request.getParameter("quantity")));
				service2.insertCart(cartVO);
				List<CartVO> cartList = service2.listCart(loginUser.getId());
				List<CartVO> orderin = new ArrayList<>();
				int cseq = service.maxCSEQ();
				for(CartVO cart : cartList) {
					if(cart.getCseq() == cseq) {
						orderin.add(cart);
					}
				}
				maxOseq = service.insertOrder(orderin, loginUser.getId());
			}
		}
				

			url =  request.getContextPath()+"/order/orderList?oseq=" + maxOseq;
			System.out.println(url);
			response.sendRedirect(url);
		}
		
	
	
	
	
	public void OrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/mypage/orderList.jsp";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			url = "/member/login.jsp";
		} else {
			int oseq = Integer.parseInt(request.getParameter("oseq"));
			ArrayList<OrderVO> orderList = service.listOrderById(loginUser.getId(), "1", oseq);

			int totalPrice = 0;
			for (OrderVO orderVO : orderList) {
				totalPrice += orderVO.getPrice2() * orderVO.getQuantity();
			}

			request.setAttribute("orderList", orderList);
			request.setAttribute("totalPrice", totalPrice);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
