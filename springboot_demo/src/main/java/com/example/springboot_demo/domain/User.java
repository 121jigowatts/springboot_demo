package com.example.springboot_demo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class User {
	@Id
	private String id;

	private String name;

	private String password;

	private String mail;

	private Integer age;

	@OneToMany(cascade=CascadeType.ALL)
	private List<Message> messages;

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public User() {

	}

	public User(String name, String mail, Integer age) {
		this.name = name;
		this.mail = mail;
		this.age = age;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
