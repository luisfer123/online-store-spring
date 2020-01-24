package com.online.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/users")
public class UserController {
	
	@RequestMapping(value = "/{id}")
	public ModelAndView userDetails(@PathVariable("id") Long id, ModelMap model) {
		
		return new ModelAndView("user_details", model);
	}

}
