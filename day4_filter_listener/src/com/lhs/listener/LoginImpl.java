package com.lhs.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

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
