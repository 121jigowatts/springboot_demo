package com.example.springboot_demo.apiControllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_demo.domain.User;

@RestController
public class SimpleApiController {

	@CrossOrigin
	@RequestMapping("/api/user/{name}")
	public User getUserInfo(@PathVariable String name) {
		return new User(name, "alice@example.com", 18);
	}
}
