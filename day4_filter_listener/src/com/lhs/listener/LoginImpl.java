package com.lhs.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/*
 * 1. HttpSessionBindingListener 를 구현하여 세션에 바인딩 이벤트를 처리하는 이벤트 핸들러가 구현되어있다.
 * HttpSessionBindingListener
 * valueBound() : 세션에 바인딩 객체를 알려주는 이벤트 발생 시, 처리
 * valueUnbound() : 세션에 언바인딩 된 객체를 알려주는 이벤트 발생 시, 처리
 * 
 * 2. 세션에 바인딩 시, valueBound() 가 호출되어 static 변수인 total의 값을 1 증가시킨다.
 */
@WebListener
public class LoginImpl implements HttpSessionBindingListener {

	String id;
	String pw;
	static int total = 0;
	
	public LoginImpl() {
	}

	public LoginImpl(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("사용자 접속");
		++total;
	}


	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("사용자 접속 해제");
		total--;
	}
	

}
