package com.nonage.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.nonage.dao.AddressDAO;
import com.nonage.dao.MemberDAO;
import com.nonage.vo.AddressVO;
import com.nonage.vo.MemberVO;

public class MemberService {

	private MemberDAO memberDAO = MemberDAO.getInstance();
	private AddressDAO addressDAO = AddressDAO.getInstance();
	
	public MemberService() {
	}

	public MemberVO login(String id, String pwd) {	
		MemberVO memberVO = memberDAO.getMember(id);
		if (memberDAO.getMember(id) != null) {
			if (memberVO.getPwd().equals(pwd))
				return memberVO;
		}
		return null;
	}

	public void join(HttpServletRequest request) {
		MemberVO memberVO = new MemberVO();

		memberVO.setId(request.getParameter("id"));
		memberVO.setPwd(request.getParameter("pwd"));
		memberVO.setName(request.getParameter("name"));
		memberVO.setEmail(request.getParameter("email"));
		memberVO.setZipNum(request.getParameter("zipNum"));
		memberVO.setAddress(request.getParameter("addr1") + request.getParameter("addr2"));
		memberVO.setPhone(request.getParameter("phone"));
		
		memberDAO.insertMember(memberVO);	
	}
	
	public int idCheckForm(String id) {
		return memberDAO.confirmID(id);
	}

	public ArrayList<AddressVO> findZipNum(String dong) {
		if (dong != null && dong.trim().equals("") == false) {
			return addressDAO.selectAddressByDong(dong.trim());
		}
		return null;		
	}
	
	public String findId(String name, String email) {
        return memberDAO.findId(name, email);
    }
    
    public String findPwd(String id, String name, String email) {
        return memberDAO.findPwd(id, name, email);
    }
	

}
