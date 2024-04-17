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
import com.oracle.coherence.spring.session.CoherenceIndexedSessionRepository;
import com.oracle.coherence.spring.session.config.annotation.web.http.EnableCoherenceHttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.oracle.coherence.spring.configuration.annotation.EnableCoherence;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.session.FlushMode;
import org.springframework.session.config.SessionRepositoryCustomizer;

import java.time.Duration;

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
public class CoherenceSessionConfiguration {

	static {
		System.setProperty("coherence.localhost", "127.0.0.1");
		System.setProperty("coherence.ttl", "0");
		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("coherence.wka", "127.0.0.1");
	}

	@Bean
	public SessionConfigurationBean sessionConfigurationBeanDefault() {
		final SessionConfigurationBean sessionConfigurationBean =
				new SessionConfigurationBean();
		sessionConfigurationBean.setType(SessionType.CLIENT);
		sessionConfigurationBean.setConfig("remote-cache-config.xml");
		return sessionConfigurationBean;
	}

	/**
	 * From the <a href="https://docs.spring.io/spring-framework/docs/5.3.34/reference/html/core.html#beans-postconstruct-and-predestroy-annotations">Spring documentation</a>:
	 *
	 * "A default lenient embedded value resolver is provided by Spring. It will try to resolve the property value and
	 * if it cannot be resolved, the property name (for example ${catalog.name}) will be injected as the value. If you
	 * want to maintain strict control over nonexistent values, you should declare a PropertySourcesPlaceholderConfigurer
	 * bean."
	 *
	 * I prefer to be strict. This is why I am declaring a {@link PropertySourcesPlaceholderConfigurer} bean here.
	 *
	 * @return the PropertySourcesPlaceholderConfigurer
	 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/support/PropertySourcesPlaceholderConfigurer.html">PropertySourcesPlaceholderConfigurer</a>
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/**
	 * A {@link SessionRepositoryCustomizer} bean is used to customize the {@link CoherenceIndexedSessionRepository}.
	 * Using the {@code @Value} annotation, the session timeout can be set via the {@code sessionTimeoutInSeconds} property,
	 * from e.g. System properties or environment variables.
	 *
	 * @param sessionTimeoutInSeconds if not set, the default value of 1234 seconds will be used
	 * @return the SessionRepositoryCustomizer bean
	 */
	@Bean
	public static SessionRepositoryCustomizer<CoherenceIndexedSessionRepository> sessionRepositoryCustomizer(
			@Value("${sessionTimeoutInSeconds:1234}") int sessionTimeoutInSeconds) {
		return (sessionRepository) -> {
			sessionRepository.setDefaultMaxInactiveInterval(Duration.ofSeconds(sessionTimeoutInSeconds));
		};
	}
}
