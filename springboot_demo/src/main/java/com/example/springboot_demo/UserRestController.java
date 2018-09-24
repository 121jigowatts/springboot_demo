package com.example.springboot_demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_demo.domain.User;
import com.example.springboot_demo.services.UserService;

@RestController
public class UserRestController {
	@Autowired
	private UserService service;

	@RequestMapping("/users")
	public List<User> getAll() {
		return service.getAll();
	}

	@RequestMapping("/users/{num}")
	public User getBy(@PathVariable int num) {
		return service.get(num);
	}

}
