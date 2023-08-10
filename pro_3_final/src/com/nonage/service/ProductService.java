package com.nonage.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.nonage.dao.ProductDAO;
import com.nonage.vo.ProductVO;

public class ProductService {

	private static ProductService instance = new ProductService();
	private final ProductDAO dao = ProductDAO.getInstance();
	
	private ProductService() {
	}
	
	public static ProductService getInstance() {
		return instance;
	}
	
	public ArrayList<ProductVO> listNewProduct() {
		return dao.listNewProduct();
	}
	public ArrayList<ProductVO> listBestProduct() {
		return dao.listBestProduct();
	}
	public ProductVO getProduct(String pseq) {
		return dao.getProduct(pseq);
	}
	public ArrayList<ProductVO> listKindProduct(String kind) {
		return dao.listKindProduct(kind);
	}
	public void home(HttpServletRequest request) {
		request.setAttribute("newProductList", dao.listNewProduct());
		request.setAttribute("bestProductList", dao.listBestProduct());
	}
	
}
