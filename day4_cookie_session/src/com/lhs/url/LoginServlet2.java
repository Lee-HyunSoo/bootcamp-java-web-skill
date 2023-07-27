package com.lhs.url;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lhs.dao.MemberDAO;
import com.lhs.vo.MemberVO;

@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		MemberVO memberVO = new MemberVO();
		MemberDAO memberDAO = new MemberDAO();
		memberVO.setId(id);
		memberVO.setPwd(pw);
		
		System.out.println(memberVO);
		boolean result = memberDAO.isExisted(memberVO);
		System.out.println(result);
		if (result) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogin", true);
			session.setAttribute("id", id);
			session.setAttribute("pw", pw);
			
			out.print("<html><body>");
			out.print("안녕하세요 " + id + "님!!!<br>");
			out.print("<a href='show'>회원정보 보기</a>");
			out.print("</body></html>");	
		}
		else {
			out.print("<html><body>");
			out.print("<center>회원아이디가 틀립니다.");
			out.print("<a href='login3.html'>다시 로그인하기</a>");
			out.print("</body></html>");	
		}
		
	}


}
