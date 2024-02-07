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
package com.oracle.coherence.spring.demo.initializer;

import com.oracle.coherence.spring.demo.config.AppConfig;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * This class is used to initialize the Spring Application.
 */
@Order(1)
public class MainApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
	}

	@Override
	protected Filter[] getServletFilters() {
		DelegatingFilterProxy delegateFilterProxy = new DelegatingFilterProxy();
		delegateFilterProxy.setTargetBeanName("myCustomFilter");
		return new Filter[]{new RequestContextFilter(), delegateFilterProxy};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
