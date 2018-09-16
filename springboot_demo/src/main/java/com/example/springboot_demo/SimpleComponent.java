package com.example.springboot_demo;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleComponent {

	@Autowired
	public SimpleComponent() {
		
	}
	
	public String now() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime().toString();
	}
}
