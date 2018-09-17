package com.example.springboot_demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	SimpleBean simpleBean() {
		return new SimpleBean();
	}
}
