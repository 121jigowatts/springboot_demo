package com.example.springboot_demo;

import java.util.Calendar;

public class SimpleBean {
	public String now() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime().toString();
	}
}
