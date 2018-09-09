package com.example.springboot_demo;

import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboot_demo.repositories.UserRepository;

@Controller
public class HelloController {
	@Autowired
	UserRepository repository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute("formModel") User userEntity, ModelAndView model) {
		model.setViewName("index");
		model.addObject("message", "Hello World!");
		Iterable<User> list = repository.findAll();
		model.addObject("users", list);
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView form(@ModelAttribute("formModel") User userEntity, ModelAndView model) {
		repository.saveAndFlush(userEntity);
		return new ModelAndView("redirect:/");
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
