package com.nonage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.nonage.service.MemberService;

@WebServlet("/members/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberService memberService = new MemberService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path = request.getPathInfo();
		
		if (path.equals("/loginForm"))
			loginForm(request, response);
		else if (path.equals("/login"))
			login(request, response);	
		else if (path.equals("/logout"))
			logout(request, response);	
		else if (path.equals("/contract"))
			contract(request, response);	
		else if (path.equals("/joinForm"))
			joinForm(request, response);	
		else if (path.equals("/join"))
			join(request, response);	
		else if (path.equals("/idCheckForm"))
			idCheckForm(request, response);
		else if (path.equals("/findZipNum"))
			findZipNum(request, response);
		else if(path.equals("/findIdForm")) // 아이디 찾기
			findIdForm(request, response);
		else if(path.equals("/findPwdForm")) // 비밀번호 찾기
			findPwdForm(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/member/login.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/member/login_fail.jsp";
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		HttpSession session = request.getSession();
	
		if (memberService.login(id, pwd) != null) {
			session.removeAttribute("id");
			session.setAttribute("loginUser", memberService.login(id, pwd));
			response.sendRedirect(request.getContextPath() + "/products");
			return;
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();
		
		response.sendRedirect(request.getContextPath() + "/products");
	}
	
	private void contract(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/member/contract.jsp";
		request.getRequestDispatcher(url).forward(request, response);	
	}
	
	private void joinForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/member/join.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/member/login.jsp";

		HttpSession session = request.getSession();
		session.setAttribute("id", request.getParameter("id"));
		memberService.join(request);

		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void idCheckForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String url = "/member/idcheck.jsp";  
		String id = request.getParameter("id").trim();
	    int message = memberService.idCheckForm(id);
        
	    request.setAttribute("message", message);
	    request.setAttribute("id", id);
	    request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void findZipNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/member/findZipNum.jsp";
	
		request.setAttribute("addressList", memberService.findZipNum(request.getParameter("dong")));
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void findIdForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
//		System.out.println("name : " + name);
//		System.out.println("email : " + email);
		String memberId = "";
		if (name != null && !name.trim().equals("")
				&& email != null && !email.trim().equals("")) {
			
			memberId = memberService.findId(name, email);
			request.setAttribute("memberId", memberId);
		}
//		System.out.println("memberId : " + memberId);
		String nextPage = "/member/findId.jsp";
		request.getRequestDispatcher(nextPage).forward(request, response);    
	}
	
	private void findPwdForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
//		System.out.println("id : " + id);
//		System.out.println("name : " + name);
//		System.out.println("email : " + email);
		String memberPwd = "";
		if (id != null && !id.trim().equals("")
				&& name != null && !name.trim().equals("")
				&& email != null && !email.trim().equals("")) {
			
			memberPwd = memberService.findPwd(id, name, email);
			request.setAttribute("id", id);
			request.setAttribute("email", email);
			request.setAttribute("memberPwd", memberPwd);
		}
//		System.out.println("memberPwd : " + memberPwd);
		String nextPage = "/member/findPassword.jsp";
		request.getRequestDispatcher(nextPage).forward(request, response);    
	}

}
