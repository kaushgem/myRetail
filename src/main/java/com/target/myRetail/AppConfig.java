package com.target.myRetail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	private final static Logger LOG = LoggerFactory.getLogger(AppConfig.class);

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
