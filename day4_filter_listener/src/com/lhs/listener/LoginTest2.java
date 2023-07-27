package com.lhs.listener;

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
import javax.servlet.http.HttpSession;

@WebServlet("/login2")
public class LoginTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext context = null;
	List users = new ArrayList();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		context = getServletContext();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		LoginImpl loginUser = new LoginImpl(id, pw);
		if (session.isNew()) {
			session.setAttribute("loginUser", loginUser);
			users.add(id);
			context.setAttribute("users", users);
		}
		out.print("<head>");
		out.print("<script type='text/javascript'>");
		out.print("setTimeout('history.go(0);', 5000)");
		out.print("</script>");
		out.print("</head>");
		out.print("<html><body>");
		out.print("아이디는 " + loginUser.id + "<br>");
		out.print("총 접속자 수는 " + LoginImpl.total + "<br>");
		out.print("접속 아이디 : <br>");
		List lst = (ArrayList)context.getAttribute("users");
		for (int i = 0; i < lst.size(); i++)
			out.print(lst.get(i) + "<br>");
		out.print("<a href='logout?id=" + id + "'>로그아웃</a>");
		out.print("</html></body>");
		out.close();
	}

}
