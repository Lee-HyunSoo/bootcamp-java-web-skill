package com.nonage.service.admin;

import java.util.ArrayList;

import com.nonage.dao.OrderDAO;
import com.nonage.vo.OrderVO;

public class AdminOrderService {

	private final OrderDAO orderDAO = OrderDAO.getInstance();
	
	public AdminOrderService() {
	}

	public ArrayList<OrderVO> adminOrderList(String key) {
		return orderDAO.listOrder(key);
	}

	public void adminOrderSave(String[] resultArr) {
		for (String oseq : resultArr) {
			System.out.println(oseq);
			orderDAO.updateOrderResult(oseq);
		}
	}

}
