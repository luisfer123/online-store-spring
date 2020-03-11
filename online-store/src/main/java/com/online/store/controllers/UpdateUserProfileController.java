package com.online.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.online.store.services.interfaces.IUpdateUserProfileService;

@Controller
@RequestMapping(value = "/users")
public class UpdateUserProfileController {
	
	@Autowired
	private IUpdateUserProfileService updateService;
	
	@RequestMapping(
			value = "/update",
			method = RequestMethod.POST, 
			params = {"newFieldValue", "fieldToUpdate"})
	public ModelAndView updateUserStringFields(
			@RequestParam("newFieldValue") String newFieldValue,
			@RequestParam("fieldToUpdate") String fieldToUpdate,
			ModelMap model) {
		
		updateService.updateUserStringFields(newFieldValue, fieldToUpdate);
		
		return new ModelAndView("redirect:/users/my_profile");
	}
	
	@RequestMapping(
			value = "/update",
			params = "new_password",
			method = RequestMethod.POST)
	public ModelAndView updateUserPassword(
			@RequestParam("new_password") String newPassword,
			ModelMap model) {
		
		System.out.println("updateUserPassword controller's method executed!");
		
		return new ModelAndView("redirect:/users/my_profile");
	}
	
	@RequestMapping(
			value = "/updateImage",
			method = RequestMethod.POST)
	public ModelAndView updateUserProfileImage(
			@RequestParam(value = "new_profile_image", required = true) CommonsMultipartFile newProfileImage,
			ModelMap model) {
				
		if(newProfileImage != null && !newProfileImage.isEmpty()) {
			updateService.updateUserProfileImage(newProfileImage.getBytes());
		}
		
		return new ModelAndView("redirect:/users/my_profile");
	}

}
