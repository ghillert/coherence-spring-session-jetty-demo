package com.oracle.coherence.spring.demo.configuration;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		System.out.println("MyHttpSessionListener - Session Created " + httpSessionEvent.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		System.out.println("MyHttpSessionListener - Session destroyed " + httpSessionEvent.getSession().getId());
	}
}
