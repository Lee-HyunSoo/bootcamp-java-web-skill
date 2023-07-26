package com.lhs.send;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "after", urlPatterns = "/second")
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		// 1. 단순 화면 이동 결과
		PrintWriter out = response.getWriter();
		
		out.print("<html><body>");
		out.print("1. sendRedirect를 이용한 redirect 실습입니다.");
		out.print("</html></body>");
		out.print("<br/><br/>");
		
		// 2. 파라미터와 화면 이동 결과
		out.print("<html><body>");
		out.print("2. name : " + request.getParameter("name"));
		out.print("</html></body>");
		out.print("<br/><br/>");
		
		// 3. Refresh 화면 이동 결과
		out.print("<html><body>");
		out.print("3. Refresh를 이용한 redirect 실습입니다.");
		out.print("</html></body>");
		out.print("<br/><br/>");
		
		// 4. javascript 화면 이동 결과
		out.print("<html><body>");
		out.print("4. javascript를 이용한 redirect 실습입니다.");
		out.print("</html></body>");
		out.print("<br/><br/>");
		
		// 5. RequestDispatcher 화면 이동 결과
		out.print("<html><body>");
		out.print("5. name : " + request.getParameter("name"));
		out.print("</html></body>");
		out.print("<br/><br/>");
		
		// 6. RequestDispatcher 객체 전달 결과
		out.print("<html><body>");
		out.print("6. dispatcher를 통해 전달 받은 객체의 값 : " + request.getAttribute("key"));
		out.print("</html></body>");
		out.print("<br/><br/>");
		request.removeAttribute("key"); // 전달받은 객체 제거
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
