package com.example.springboot_demo;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Message {
	@Id
	private String id;

	private String text;
	private Date date;

	public Message(String text) {
		super();
		this.text = text;
		this.date = new Date();
	}

	public String getId() {
		return this.id;
	}

	public String getText() {
		return this.text;
	}

	public Date getDate() {
		return this.date;
	}

}
