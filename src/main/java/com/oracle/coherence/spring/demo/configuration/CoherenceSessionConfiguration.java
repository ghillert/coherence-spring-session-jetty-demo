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
package com.oracle.coherence.spring.demo.configuration;

import com.oracle.coherence.spring.configuration.session.SessionConfigurationBean;
import com.oracle.coherence.spring.configuration.session.SessionType;
import com.oracle.coherence.spring.session.config.annotation.web.http.EnableCoherenceHttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.coherence.spring.configuration.annotation.EnableCoherence;
import org.springframework.session.FlushMode;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * @author Gunnar Hillert
 */
@Configuration
@EnableCoherence
@EnableCoherenceHttpSession(
		cache = "spring:session:sessions",
		flushMode = FlushMode.ON_SAVE,
		sessionTimeoutInSeconds = 1800,
		useEntryProcessor = false
)
public class CoherenceSessionConfiguration extends AbstractHttpSessionApplicationInitializer {

	@Bean
	public SessionConfigurationBean sessionConfigurationBeanDefault() {
		final SessionConfigurationBean sessionConfigurationBean =
				new SessionConfigurationBean();
		sessionConfigurationBean.setType(SessionType.CLIENT);
		sessionConfigurationBean.setConfig("remote-cache-config.xml");
		return sessionConfigurationBean;
	}

}
