package com.example.springboot_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboot_demo.domain.Message;
import com.example.springboot_demo.domain.User;
import com.example.springboot_demo.services.AccountService;
import com.example.springboot_demo.services.MessageService;

@RestController
public class HomeController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private MessageService service;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("/home/index");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = accountService.findUserByName(auth.getName());
		mav.addObject("currentUser", user);
		mav.addObject("fullName", "Welcome " + user.getName());
		Iterable<Message> messages = service.findTop3ByOrderByDateDesc();
		mav.addObject("messages", messages);
		return mav;
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView form(@ModelAttribute("formModel") Message message, ModelAndView model) {
		service.save(message);
		return new ModelAndView("redirect:/home/");
	}
}
