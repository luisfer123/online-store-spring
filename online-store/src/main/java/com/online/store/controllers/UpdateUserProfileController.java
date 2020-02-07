package com.online.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.online.store.services.UpdateUserProfileService;

@Controller
@RequestMapping(value = "/users")
public class UpdateUserProfileController {
	
	@Autowired
	private UpdateUserProfileService updateService;
	
	@RequestMapping(value = "/update_first_name", method = RequestMethod.POST)
	public ModelAndView updateFirstName(
			@RequestParam("updateFirstName") String newFirstName,
			ModelMap model) {
				
		updateService.updateFirstName(newFirstName);
		
		return new ModelAndView("redirect:/users/my_profile");
	}
	
	@RequestMapping(value = "/update_last_name", method = RequestMethod.POST)
	public ModelAndView updateLastName(
			@RequestParam("updateLastName") String newFirstName,
			ModelMap model) {
						
		return new ModelAndView("redirect:/users/my_profile");
	}

}
