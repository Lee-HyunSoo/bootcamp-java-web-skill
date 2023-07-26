package com.lhs.context;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cset")
public class SetServletContext extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		/*
		 * ServletContext는 Application에서 얻어오는 것이기 때문에 new로 생성하는게 아니라 가져오는 것이다.
		 * 1. getServletContext() 를 이용해 ServletContext 객체에 접근
		 * 2. ArrayList에 이름과 나이를 저장
		 * 3. 다시 ServletContext 객체에 setAttribute() 를 이용해 바인딩
		 */
		
		ServletContext context = getServletContext();
		/*
		 * ArrayList는 타입을 지정해주지 않아도 넣으면 다 들어간다.
		 * 다만, add 시 값이 그대로 들어가는게 아니라 Object로 감싸져서 들어간다.
		 */
		List members = new ArrayList<>();
		members.add("이순신");
		members.add(30);
		context.setAttribute("members", members);
		
		out.print("<html><body>");
		out.print("이순신과 30 설정");
		out.print("</html></body>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
