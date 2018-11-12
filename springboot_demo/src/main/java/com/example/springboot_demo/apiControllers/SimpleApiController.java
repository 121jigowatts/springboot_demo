package com.example.springboot_demo.apiControllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_demo.domain.Message;
import com.example.springboot_demo.domain.User;

@RestController
public class SimpleApiController {

	@CrossOrigin
	@RequestMapping("/api/user/{name}")
	public User getUserInfo(@PathVariable String name) {
		return new User(name, "alice@example.com", 18);
	}

	@CrossOrigin
	@RequestMapping("/api/messages")
	public List<Message> getMessages() {
		List<Message> list = new ArrayList<Message>();
		list.add(newMessage("1", "hello"));
		list.add(newMessage("2", "world"));
		list.add(newMessage("3", "bye"));

		return list;
	}

	private Message newMessage(String id, String text) {
		User user = new User("Alice", "Alice@example.com", 18);

		Message m = new Message();
		m.setId(id);
		m.setText(text);
		m.setDate(new Date());
		m.setUser(user);
		return m;
	}
}
