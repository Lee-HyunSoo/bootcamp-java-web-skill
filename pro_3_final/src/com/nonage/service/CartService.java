package com.nonage.service;

import java.util.List;

import com.nonage.dao.CartDAO;
import com.nonage.vo.CartVO;

public class CartService {
	
	private static CartService instance = new CartService();
	private final CartDAO dao = CartDAO.getInstance();
	
	private CartService() {
	}
	
	public static CartService getInstance() {
		return instance;
	}

	public List<CartVO> listCart(String id){
		return  dao.listCart(id);
	}
	
	public void deleteCart(int cseq) {
		dao.deleteCart(cseq);
	}
	
	public void insertCart(CartVO vo) {
		dao.insertCart(vo);
	}
	
	
}
