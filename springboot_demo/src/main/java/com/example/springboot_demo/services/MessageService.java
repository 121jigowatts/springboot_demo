package com.example.springboot_demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.springboot_demo.domain.Message;
import com.example.springboot_demo.repositories.MessageRepository;

@Component
public class MessageService {
	@Autowired
	MessageRepository repository;

	public List<Message> getAll() {
		return repository.findAll();
	}

	public List<Message> findTop3ByOrderByDateDesc() {
		return repository.findTop3ByOrderByDateDesc();
	}

	public Optional<Message> findById(String id) {
		return repository.findById(id);
	}

	public void save(Message message) {
		repository.save(message);
	}

}
