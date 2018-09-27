package com.example.springboot_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboot_demo.domain.Message;
import com.example.springboot_demo.services.MessageService;

@RestController
public class HomeController {

	@Autowired
	private MessageService service;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("/home/index");
		Iterable<Message> messages = service.findTop3ByOrderByDateDesc();
		mav.addObject("messages", messages);
		return mav;
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView form(@RequestParam("text") String text, ModelAndView model) {
		Message message = new Message(text);
		service.save(message);
		return new ModelAndView("redirect:/home/");
	}
}
