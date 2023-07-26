package com.lhs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * load-on-startup 기능
 * 1. 서블릿은 브라우저에서 최초 요청 시 init() 메서드를 실행한 후 메모리에 로드되어 기능을 수행한다.
 * 2. 따라서 최초 요청에 대해서는 실행시간이 길어질 수 밖에 없다. 이 단점을 보완하기 위해 loadOnStartup을 사용한다.
 * 3. loadOnStartup의 속성값을 설정하지 않으면 0으로 설정 되어있다. 숫자는 우선순위를 의미한다. 낮을수록 높다.
 */

@WebServlet(name = "loadConfig",
			description = "web.xml에서 공통 메뉴 읽기",
			urlPatterns = "/loadConfig",
			loadOnStartup = 1)
public class LoadAppConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("LoadAppConfig의 init 메서드 호출");
		context = config.getServletContext();
		
		context.setAttribute("menu_member", context.getInitParameter("menu_member"));
		context.setAttribute("menu_order", context.getInitParameter("menu_order"));
		context.setAttribute("menu_goods", context.getInitParameter("menu_goods"));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<html><body>");
		out.print("<table border = 1 cellspacing = 0>");
		out.print("<tr>메뉴이름</tr>");
		out.print("<tr><td>" + (String)context.getAttribute("menu_member") + "</td></tr>");
		out.print("<tr><td>" + (String)context.getAttribute("menu_order") + "</td></tr>");
		out.print("<tr><td>" + (String)context.getAttribute("menu_goods") + "</td></tr>");
		out.print("</table>");
		out.print("</html></body>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
