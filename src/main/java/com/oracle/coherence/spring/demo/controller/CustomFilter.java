/*
 * Copyright 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oracle.coherence.spring.demo.controller;

import com.oracle.coherence.spring.demo.pof.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomFilter extends GenericFilterBean {

	private User user;

	public CustomFilter(User user) {
		System.out.println("CustomFilter constructor");
		this.user = user;
	}

	@Override
	public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(true);

		user.setEmail("tttttt");
		user.setName("fggdfgdfgdfgdf");

		if (session != null) {

			System.out.println("Custom filter! - Session ID: " + session.getId());
			session.setAttribute("foo22", "bar");
		}
		else {
			System.out.println("Custom filter! - No session available (skip setting attribute).");
		}

		chain.doFilter(request, response);
	}
}
