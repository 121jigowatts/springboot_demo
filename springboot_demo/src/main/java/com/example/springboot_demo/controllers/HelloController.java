package com.example.springboot_demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboot_demo.domain.Message;
import com.example.springboot_demo.domain.User;
import com.example.springboot_demo.repositories.MessageRepository;
import com.example.springboot_demo.repositories.UserRepository;
import com.example.springboot_demo.services.UserService;

@Controller
public class HelloController {
	@Autowired
	UserRepository repository;

	@Autowired
	private UserService service;
	
	@Autowired
	MessageRepository repo;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("index");
		model.addObject("message", "From MongoDB");
		List<Message> messages = repo.findAll();
		model.addObject("messages", messages);
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView form(@RequestParam("text") String text,ModelAndView model) {
		Message message = new Message(text);
		repo.save(message);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model, Pageable pageable) {
		model.setViewName("index");
		Page<User> users = repository.findAll(pageable);
		model.addObject("users", users);
		return model;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(ModelAndView model) {
		model.setViewName("search");
		model.addObject("value", "");
		Iterable<User> users = service.getAll();
		model.addObject("users", users);
		return model;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView select(@RequestParam String cstr, ModelAndView model) {
		model.setViewName("search");
		if (cstr == "") {
			model = new ModelAndView("redirect:/search");
		} else {
			model.addObject("value", cstr);
			List<User> users = service.search(cstr);
			model.addObject("users", users);
		}
		return model;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute User userEntity, @PathVariable int id, ModelAndView model) {
		model.setViewName("edit");
		Optional<User> user = repository.findById((long) id);
		model.addObject("formModel", user.get());
		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView update(@ModelAttribute User userEntity, ModelAndView model) {
		repository.saveAndFlush(userEntity);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id, ModelAndView model) {
		model.setViewName("delete");
		Optional<User> user = repository.findById((long) id);
		model.addObject("formModel", user.get());
		return model;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView remove(@RequestParam long id, ModelAndView model) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/");
	}

	@PostConstruct
	public void initalData() {
		ArrayList<User> data = new ArrayList<User>();
		data.add(new User("Alice", "Alice@example.com", 16));
		data.add(new User("Emma", "Emma@example.com", 19));
		data.add(new User("Olivia", "Olivia@example.com", 17));

		for (User user : data) {
			repository.saveAndFlush(user);
		}
	}
}
