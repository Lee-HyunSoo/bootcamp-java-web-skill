package com.nonage.service.admin;

import java.util.ArrayList;

import com.nonage.dao.QnaDAO;
import com.nonage.vo.QnaVO;

public class AdminQnAService {

	private final QnaDAO qnaDAO = QnaDAO.getInstance();
	
	public AdminQnAService() {
	}

	public ArrayList<QnaVO> adminQnAList() {
		return qnaDAO.listAllQna();
	}

	public QnaVO adminQnADetail(String qseq) {
		return qnaDAO.getQna(Integer.parseInt(qseq));
	}

	public void adminQnARepSave(String qseq, String reply) {
		QnaVO qnaVO = new QnaVO();
		qnaVO.setQseq(Integer.parseInt(qseq));
		qnaVO.setReply(reply);

		qnaDAO.updateQna(qnaVO);
	}
	
	

}
