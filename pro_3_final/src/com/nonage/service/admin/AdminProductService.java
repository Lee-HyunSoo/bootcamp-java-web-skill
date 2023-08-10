package com.nonage.service.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.nonage.dao.ProductDAO;
import com.nonage.vo.ProductVO;
import com.oreilly.servlet.MultipartRequest;


public class AdminProductService {

	private final ProductDAO productDAO = ProductDAO.getInstance();
	
	public AdminProductService() {
	}

	public Map<String, Object> adminProductList(String key, String tpage, String path) {
		ArrayList<ProductVO> productList = productDAO.listProduct(Integer.parseInt(tpage), key);
		String paging = productDAO.pageNumber(Integer.parseInt(tpage), key, path);
		
		Map<String, Object> model = new HashMap<>();
		model.put("productList", productList);
		model.put("productListSize", productList.size());
		model.put("paging", paging);
		
		return model;
	}

	public ProductVO adminProductDetail(String pseq) {
		return productDAO.getProduct(pseq);
	}

	public ProductVO adminProductUpdateForm(String pseq) {
		return productDAO.getProduct(pseq);	
	}

	public void adminProductUpdate(MultipartRequest multi) {
		ProductVO productVO = new ProductVO();
		productVO.setPseq(Integer.parseInt(multi.getParameter("pseq")));
		productVO.setKind(multi.getParameter("kind"));
		productVO.setName(multi.getParameter("name"));
		productVO.setPrice1(Integer.parseInt(multi.getParameter("price1")));
		productVO.setPrice2(Integer.parseInt(multi.getParameter("price2")));
		productVO.setPrice3(Integer.parseInt(multi.getParameter("price2")) - Integer.parseInt(multi.getParameter("price1")));
		productVO.setContent(multi.getParameter("content"));
		
		if (multi.getFilesystemName("image") == null)
			productVO.setImage(multi.getParameter("nonmakeImg"));
		else 
			productVO.setImage(multi.getFilesystemName("image"));
	
		if ((multi.getParameter("bestyn") == null))
			productVO.setBestyn("n");
		else
			productVO.setBestyn(multi.getParameter("bestyn"));			
	
		if ((multi.getParameter("useyn") == null))
			productVO.setUseyn("n");
		else
			productVO.setUseyn(multi.getParameter("useyn"));			
		
		productDAO.updateProduct(productVO);
	}

	public void adminProductWrite(MultipartRequest multi) {
		ProductVO productVO = new ProductVO();
		productVO.setKind(multi.getParameter("kind"));
		productVO.setName(multi.getParameter("name"));
		productVO.setPrice1(Integer.parseInt(multi.getParameter("price1")));
		productVO.setPrice2(Integer.parseInt(multi.getParameter("price2")));
		productVO.setPrice3(
				Integer.parseInt(multi.getParameter("price2")) - Integer.parseInt(multi.getParameter("price1")));
		productVO.setContent(multi.getParameter("content"));
		productVO.setImage(multi.getFilesystemName("image"));
		
		if ((multi.getParameter("bestyn") == null))
			productVO.setBestyn("n");
		else
			productVO.setBestyn(multi.getParameter("bestyn"));			
	
		if ((multi.getParameter("useyn") == null))
			productVO.setUseyn("n");
		else
			productVO.setUseyn(multi.getParameter("useyn"));			

		productDAO.insertProduct(productVO);	
	}

	
	
	public void adminProductDelete(int pseq) {
		productDAO.deleteProduct(pseq);
	}
	
	
	
	
	
}
