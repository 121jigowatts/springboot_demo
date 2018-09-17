package com.example.springboot_demo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "findByName", query = "from User where name like :cname"),
		@NamedQuery(name = "findByAge", query = "from User where age > :min and age < :max") })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@NotNull
	private long id;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String name;

	@Column(length = 100, nullable = true)
	@Email
	private String mail;

	@Column(nullable = true)
	@Min(0)
	@Max(200)
	private Integer age;

	@OneToMany(cascade = CascadeType.ALL)
	@Column(nullable = true)
	private List<Task> tasks;
	
	public User() {

	}

	public User(String name, String mail, Integer age) {
		this.name = name;
		this.mail = mail;
		this.age = age;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public List<Task> getTasks(){
		return this.tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
