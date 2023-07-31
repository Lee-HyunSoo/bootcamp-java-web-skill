package com.lhs.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 * JSON : 자바스크립트 객체
 * 서로 다른 언어끼리 데이터를 주고받을 경우 사용한다.
 * 자바스크립트에서 JSON 객체 생성 : { key : value }
 * 
 * 자바에서 JSON 객체 : JSONObject json = new JSONObject();
 * json.put("key", "value");
 * 
 * 다수의 JSON 객체 동시 저장
 * JSONArray jsonArr = new JSONArray();
 * jsonArr.add(json);
 * 
 * 자바에서 JSON 객체를 Javascript 으로 전달
 * JSONObject.toJSONString();
 * 
 */
@WebServlet("/json1")
public class JsonServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String jsonInfo = request.getParameter("jsonInfo");
		try {
			JSONParser jsonParser = new JSONParser(); // 문자열을 JSON 객체로 파싱하기 위해
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonInfo); // JSON 객체 생성, 문자열을 JSON으로 파싱
			System.out.println("* 회원정보 *");
			System.out.println(jsonObject.get("name"));
			System.out.println(jsonObject.get("age"));
			System.out.println(jsonObject.get("gender"));
			System.out.println(jsonObject.get("nickname"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
