package com.online.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.store.data.entities.User;
import com.online.store.security.UtilSecurity;
import com.online.store.services.ShoppingCartService;
import com.online.store.services.UserService;

@Controller
@RequestMapping(value = "/cart")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService cartService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "")
	public ModelAndView goShoppingCart(ModelMap model) {
		
		User user =
				userService
				.findByUsername(UtilSecurity.getPrincipalUsername());
		
		System.out.println(user.getShoppingCart());
		if(user.getShoppingCart() == null 
				|| user.getShoppingCart().getProducts() == null
				|| user.getShoppingCart().getProducts().isEmpty()) {
			model.addAttribute("emptyCart", true);
			return new ModelAndView("shopping_cart", model);
		}
		
		model.addAttribute("cartProducts", user.getShoppingCart().getProducts());
		
		return new ModelAndView("shopping_cart");
	}
	
	@RequestMapping(value = "/{productId}/add")
	public ModelAndView addProduct(
			@PathVariable("productId") Long productId) {
		
		cartService.saveProductInLoggedInUserCart(productId);
		
		return new ModelAndView("redirect:/cart");
	}
	
	@RequestMapping(value = "/{productId}/delete")
	public ModelAndView deleteProduct(
			@PathVariable("productId") Long productId,
			ModelMap model) {
		
		cartService.deleteProductById(productId);
		
		return new ModelAndView("redirect:/cart", model);
	}

}
