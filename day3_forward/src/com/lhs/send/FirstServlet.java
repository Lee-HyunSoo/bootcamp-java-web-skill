package com.lhs.send;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "before", urlPatterns = "/first")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");		
		PrintWriter out = response.getWriter();
		
		// 1. 단순 화면 이동
//		response.sendRedirect("second");
		
		// 2. 파라미터와 화면 이동
//		response.sendRedirect("second?name=lee");
		
		// 3. Refresh 화면 이동 
//		response.addHeader("Refresh", "2; url=second");
		
		// 4. javascript 화면 이동
//		out.print("<script type='text/javascript'>");
//		out.print("location.href='second';");
//		out.print("</script>");
		
		// 5. RequestDispatcher 화면 이동
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher("second?name=lee");
//		requestDispatcher.forward(request, response);
		
		// 6. RequestDispatcher 이용 객체 전달 (바인딩)
		request.setAttribute("key", "value");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("second");
		requestDispatcher.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
