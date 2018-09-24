package com.example.springboot_demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

	@RequestMapping("/home/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("/home/index");
		return mav;
	}
}
