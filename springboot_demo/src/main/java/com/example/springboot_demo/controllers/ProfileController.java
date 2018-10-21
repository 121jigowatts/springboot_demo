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

import com.example.springboot_demo.domain.User;
import com.example.springboot_demo.services.AccountService;

@RestController
public class ProfileController {

	@Autowired
	private AccountService service;

	@RequestMapping(value = "/home/profile", method = RequestMethod.GET)
	public ModelAndView changeProfile(ModelAndView mav) {
		mav.setViewName("/home/profile");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = service.findUserByName(auth.getName());
		user.setPassword("");
		mav.addObject("currentUser", user);
		mav.addObject("fullName", "Welcome " + user.getName());
		mav.addObject("formModel", user);
		return mav;
	}

	@RequestMapping(value = "/home/profile", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView update(@ModelAttribute User user, ModelAndView model) {

		service.saveUser(user);

		return new ModelAndView("redirect:/home");
	}

}
