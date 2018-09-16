package com.example.springboot_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeRestController {

	@Autowired
	SimpleComponent component;
	
	@RequestMapping("/now")
	public String datetime() {
		return component.now();
	}
	
}
