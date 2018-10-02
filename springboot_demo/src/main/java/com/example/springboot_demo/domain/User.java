package com.example.springboot_demo.domain;

import javax.persistence.Id;

public class User {
	@Id
	private String id;

	private String name;

	private String password;

	private String mail;

	private Integer age;

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
