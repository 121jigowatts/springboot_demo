package com.example.springboot_demo;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboot_demo.repositories.TaskRepository;

@Controller
public class TaskController {
	@Autowired
	TaskRepository repository;

	@PersistenceContext
	EntityManager entityManager;

	TaskDaoImpl dao;

	@RequestMapping(value = "/task", method = RequestMethod.GET)
	public ModelAndView task(ModelAndView model) {
		model.setViewName("taskList");
		Task task = new Task();
		model.addObject("formModel", task);
		List<Task> tasks = (List<Task>) dao.getAll();
		model.addObject("tasks", tasks);
		return model;
	}

	@RequestMapping(value = "/task", method = RequestMethod.POST)
	public ModelAndView form(@Valid @ModelAttribute Task task, Errors result, ModelAndView model) {
		if (result.hasErrors()) {
			model.setViewName("taskList");
			model.addObject("message", "Error is occured...");
			return model;
		} else {
			repository.saveAndFlush(task);
			return new ModelAndView("redirect:/task");
		}
	}
	
	@PostConstruct
	public void init() {
		dao = new TaskDaoImpl(entityManager);
	}
}
