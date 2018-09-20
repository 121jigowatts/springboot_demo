package com.example.springboot_demo;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Message {
	@Id
	private String id;

	private String message;
	private Date date;

	public Message(String message) {
		this.message = message;
	}

	public String getId() {
		return this.id;
	}

	public String getMessage() {
		return this.message;
	}

	public Date getDate() {
		return this.date;
	}

}
