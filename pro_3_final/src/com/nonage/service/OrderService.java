package com.nonage.service;

import java.util.ArrayList;
import java.util.List;

import com.nonage.dao.OrderDAO;
import com.nonage.vo.CartVO;
import com.nonage.vo.OrderVO;

public class OrderService {

	private static OrderService instance = new OrderService();
	private final OrderDAO dao = OrderDAO.getInstance();
	
	private OrderService() {
	}
	
	public static OrderService getInstance() {
		return instance;
	}
	
	public ArrayList<Integer> selectSeqOrderIng(String id){
		return dao.selectSeqOrderIng(id);
	}
	
	public ArrayList<Integer> selectSeqOrderIng2(String id) {
		return dao.selectSeqOrderIng2(id);
	}
	
	
	
	
	public ArrayList<OrderVO> listOrderById(String id, String result, int oseq) {
		return dao.listOrderById(id, result, oseq);
	}
	
	public int insertOrder(List<CartVO> cartList, String id) {
		return dao.insertOrder(cartList, id);
	}
	public int maxCSEQ() {
		return dao.maxCSEQ();
	}
}
