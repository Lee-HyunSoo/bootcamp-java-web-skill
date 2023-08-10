package com.nonage.service.admin;

import java.util.ArrayList;

import com.nonage.dao.MemberDAO;
import com.nonage.dao.WorkerDAO;
import com.nonage.vo.MemberVO;

public class AdminMemberService {

	private final WorkerDAO workerDAO = WorkerDAO.getInstance();
	private final MemberDAO memberDAO = MemberDAO.getInstance();
	
	public AdminMemberService() {
	}

	public int adminLoginForm(String workerId, String workerPwd) {
		return workerDAO.workerCheck(workerId, workerPwd);
	}

	public ArrayList<MemberVO> adminMemberList(String key) {
		return memberDAO.listMember(key);
	}

}
