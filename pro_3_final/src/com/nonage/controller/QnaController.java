package com.nonage.controller;



import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.service.QnaService;
import com.nonage.vo.MemberVO;
import com.nonage.vo.QnaVO;

@WebServlet("/qnaPage/*")
public class QnaController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final QnaService qnaService = QnaService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getPathInfo();
        System.out.println(action);
        if (action.equals("/qnaList")) {
            listQna(req, resp);
        } else if (action.equals("/qnaDetail")) {
            detailQna(req, resp);
        } else if (action.equals("/qnaInsert")) {
            insertQna(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doGet(req, res);
    }

    private void listQna(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url =  "/qna/qnaList.jsp";

        HttpSession session = req.getSession();
    	MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        if (loginUser == null) {
            url = "/member/login.jsp";
        } else {
            List<QnaVO> qnaList = qnaService.listQna(loginUser.getId());
            req.setAttribute("qnaList", qnaList);
         
        }
        req.getRequestDispatcher(url).forward(req, resp);
    }

    private void detailQna(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/qna/qnaView.jsp";
        String qseq = req.getParameter("qseq");
        QnaVO qnaVO = qnaService.detailQna(Integer.parseInt(qseq));
        req.setAttribute("qnaVO", qnaVO);
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }

    public void insertQna(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = req.getContextPath()+"/qnaPage/qnaList";

        HttpSession session = req.getSession();
    	MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        if (loginUser == null) {
            url = req.getContextPath()+ "/member/login.jsp";
        } else {

            QnaVO qnaVO = new QnaVO();
            qnaVO.setContent(req.getParameter("content"));
            qnaVO.setSubject(req.getParameter("subject"));

            qnaService.insertQna(qnaVO, loginUser.getId());
            resp.sendRedirect(url);
        }

    }
}
