package com.example.springboot_demo.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;

public class Message {
	@Id
	private String id;

	private String text;
	private Date date;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDate(String date) {
		try {
			this.date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(date);
		} catch (ParseException e) {
			this.date = new Date();
		}
	}

	public String getStringDate() {
		if (this.date == null) {
			return "";
		}
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(this.date);
	}

}
