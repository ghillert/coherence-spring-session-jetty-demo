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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Gunnar Hillert
 */
@Controller
@RequestMapping("/")
public class HelloSpringSessionController {

	@Autowired
	private HttpSession session;

	@GetMapping("/hello")
	public String hello() {
		Integer counter = (Integer) session.getAttribute("counter");
		if (counter == null) {
			counter = 1;
			session.setAttribute("counter", counter);
		}
		else {
			counter++;
			session.setAttribute("counter", counter);
		}
		System.out.println("Session ID: " + this.session.getId() + "; counter = " + counter
				+ "; session attribute foo = " + session.getAttribute("foo"));
		return "hello";
	}
}
