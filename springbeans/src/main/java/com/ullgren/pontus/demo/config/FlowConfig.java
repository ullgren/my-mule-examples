package com.ullgren.pontus.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowConfig {
	@Bean
	public AnotherGreeterService myOtherGreeterService() {
		return new AnotherGreeterService();
	}
}
