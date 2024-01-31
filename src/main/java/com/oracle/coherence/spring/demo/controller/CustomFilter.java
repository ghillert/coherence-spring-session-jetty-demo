package com.oracle.coherence.spring.demo.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component("myCustomFilter")
public class CustomFilter extends GenericFilterBean {
	public CustomFilter() {
		System.out.println("CustomFilter constructor");
	}

	@Override
	public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);

		if (session != null) {
			System.out.println("I am just a custom filter! - Session ID: " + session.getId());
			session.setAttribute("foo", "bar");
		}

		chain.doFilter(request, response);
	}
}
