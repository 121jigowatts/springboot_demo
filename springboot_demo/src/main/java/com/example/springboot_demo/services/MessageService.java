package com.example.springboot_demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot_demo.domain.Message;
import com.example.springboot_demo.repositories.MessageRepository;

@Service
public class MessageService {
	@Autowired
	MessageRepository repository;

	public List<Message> getAll() {
		return repository.findAll();
	}

	public void save(Message message) {
		repository.save(message);
	}

}
