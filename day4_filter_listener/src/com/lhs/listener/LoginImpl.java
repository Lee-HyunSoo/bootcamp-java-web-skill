package com.lhs.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class LoginImpl implements HttpSessionListener {

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
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("세션 생성");
		++total;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("세션 소멸");
		total--;
	}

}
