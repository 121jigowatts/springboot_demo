package com.example.springboot_demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboot_demo.domain.Message;
import com.example.springboot_demo.domain.User;
import com.example.springboot_demo.services.AccountService;
import com.example.springboot_demo.services.MessageService;

@RestController
public class MessageController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/message/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable String id, ModelAndView mav) {
		mav.setViewName("/message/edit");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = accountService.findUserByName(auth.getName());
		mav.addObject("currentUser", user);
		mav.addObject("fullName", "Welcome " + user.getName());

		Optional<Message> messages = messageService.findById(id);
		mav.addObject("formModel", messages.get());
		return mav;
	}

	@RequestMapping(value = "/message/edit", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView update(@ModelAttribute("formModel") Message message, ModelAndView model) {
		messageService.save(message);
		return new ModelAndView("redirect:/home/");
	}
}
