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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@Autowired
	private User user;

	@GetMapping("/clear")
	public String clearSession() {
		System.out.println("Invalidate Session with ID: " + this.session.getId());
		session.invalidate();
		return "redirect:/hello";
	}

	@GetMapping("/user")
	public String setupUser() {
		System.out.println("Session ID: " + this.session.getId());

		System.out.println("session.getAttributeNames()");
		final var names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			System.out.println(names.nextElement());
		}
		System.out.println("=======");
		System.out.println(this.user.getName() + " " + this.user.getEmail());

		user.setName("Foo Bar");
		user.setEmail("foo@bar.com");
		System.out.println(this.user.getName() + " " + this.user.getEmail());
		return "redirect:/hello";
	}

	@GetMapping("/hello")
	public String hello(Model model) {
		Integer counter = (Integer) session.getAttribute("counter");
		if (counter == null) {
			counter = 1;
			session.setAttribute("counter", counter);
		}
		else {
			counter++;
			session.setAttribute("counter", counter);
		}

		model.addAttribute("user", this.user);

		System.out.println("Session ID: " + this.session.getId() + "; counter = " + counter
				+ "; session attribute foo = " + session.getAttribute("foo"));
		return "hello";
	}
}
