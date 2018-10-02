package com.example.springboot_demo.configuration;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//	    registry.addViewController("/home").setViewName("/home/index");
//	    registry.addViewController("/").setViewName("index");
//	    registry.addViewController("/login").setViewName("login");
	}
	
}
