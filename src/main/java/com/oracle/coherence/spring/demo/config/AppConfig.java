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
package com.oracle.coherence.spring.demo.config;

import com.oracle.coherence.spring.demo.controller.CustomFilter;
import com.oracle.coherence.spring.demo.pof.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.SpringHttpSessionConfiguration;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gunnar Hillert
 */
@Configuration
@ComponentScan(basePackages = "com.oracle.coherence.spring.demo")
@Import({SpringHttpSessionConfiguration.class, WebMvcConfig.class})
public class AppConfig {
	@Bean(name = "myCustomFilter")
	public CustomFilter myCustomFilter(User user) {
		return new CustomFilter(user);
	}

}
