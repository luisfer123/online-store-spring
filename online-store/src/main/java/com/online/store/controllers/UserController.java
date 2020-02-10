package com.online.store.controllers;

import java.util.Base64;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.online.store.data.entities.User;
import com.online.store.services.interfaces.IUserService;

@Controller
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("")
	public ModelAndView showUsers(
			@RequestParam(value = "page_number", required = false) Integer pageNumber,
			ModelMap model) {
		
		int requestedPage = pageNumber == null ? 0 : pageNumber;
		Page<User> usersPage = userService.findAllPaginated(requestedPage);
		
		if(!usersPage.hasContent())
			return new ModelAndView("/");
		
		model.addAttribute("users", usersPage.getContent());
		model.addAttribute("numberOfPages", usersPage.getTotalPages());
		model.addAttribute("currentPage", usersPage.getNumber());
		
		return new ModelAndView("users", model);
	}
	
	@RequestMapping(value = "/{id}")
	public ModelAndView userDetails(
			@PathVariable("id") Long id, 
			ModelMap model) {
		
		User user = userService.findById(id);
		model.addAttribute("user", user);
		
		return new ModelAndView("user_details", model);
	}
	
	@RequestMapping("/my_profile")
	public ModelAndView goUserProfile(ModelMap model) {
		
		org.springframework.security.core.userdetails.User principal = 
				(org.springframework.security.core.userdetails.User) 
				SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		User user = userService.findByUsername(principal.getUsername());
		model.addAttribute("userProfileImage", 
				Base64.getEncoder().encodeToString(user.getProfileImage()));
		model.addAttribute("user", user);
		
		return new ModelAndView("user_profile", model);
	}
	
	@RequestMapping(value = "/delete")
	public ModelAndView remove(
			@RequestParam("user_id") Long userId, 
			ModelMap model) {
		
		userService.delete(userId);
		
		return new ModelAndView("redirect:/users?user_deleted=true", model);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView goUserRegistration(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return new ModelAndView("registration_form", model);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView createUser(
			@Valid @ModelAttribute("user") User newUser, BindingResult result,
			@RequestParam("confirmPassword") String confirmPassword,
			ModelMap model) {
		
		if(result.hasErrors()) {
			model.addAttribute("validation_result", result);
			return new ModelAndView("registration_form", model);
		}
				
		userService.registerNewUser(newUser);
		
		return new ModelAndView("redirect:/home?user_registered=true", model);
	}

}
