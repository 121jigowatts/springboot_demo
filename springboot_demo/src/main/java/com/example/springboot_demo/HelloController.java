package com.example.springboot_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboot_demo.repositories.UserRepository;

@Controller
public class HelloController {
	@Autowired
	UserRepository repository;

	@PersistenceContext
	EntityManager entityManager;

	UserDaoImpl dao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("index");
		model.addObject("message", "User List");
		List<User> users = repository.findByAge(15, 18);
		model.addObject("users", users);
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView form(@ModelAttribute("formModel") @Validated User userEntity, BindingResult result,
			ModelAndView model) {
		ModelAndView res = null;
		if (!result.hasErrors()) {
			repository.saveAndFlush(userEntity);
			res = new ModelAndView("redirect:/");
		} else {
			model.setViewName("index");
			model.addObject("message", "Error is occured...");
			Iterable<User> users = repository.findAll();
			model.addObject("users", users);
			res = model;
		}
		return res;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(ModelAndView model) {
		model.setViewName("search");
		model.addObject("value", "");
		Iterable<User> users = dao.findByAge(15, 18);
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
			List<User> users = dao.search(cstr);
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
		dao = new UserDaoImpl(entityManager);

		ArrayList<User> data = new ArrayList<User>();
		data.add(new User("Alice", "Alice@example.com", 16));
		data.add(new User("Emma", "Emma@example.com", 19));
		data.add(new User("Olivia", "Olivia@example.com", 17));

		for (User user : data) {
			repository.saveAndFlush(user);
		}
	}
}
