package com.lhs.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LoginImpl2 implements HttpSessionBindingListener {

	String id;
	String pw;
	static int total = 0;
	
	public LoginImpl2() {
	}

	public LoginImpl2(String id, String pw) {
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
