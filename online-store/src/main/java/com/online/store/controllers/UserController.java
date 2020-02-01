package com.online.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ModelAndView userDetails(@PathVariable("id") Long id, ModelMap model) {
		
		return new ModelAndView("user_details", model);
	}
	
	@RequestMapping(value = "/delete")
	public ModelAndView remove(
			@RequestParam("user_id") Long userId, 
			ModelMap model) {
		
		userService.delete(userId);
		
		return new ModelAndView("redirect:/users?user_deleted=true", model);
	}

}
