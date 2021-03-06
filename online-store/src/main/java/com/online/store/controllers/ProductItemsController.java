package com.online.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.online.store.data.entities.ProductItem;
import com.online.store.services.ProductItemService;

@Controller
@RequestMapping(value="/products")
public class ProductItemsController {
	
	@Autowired
	private ProductItemService productItemService;
	
	@RequestMapping(value = "/{productId}/product_items")
	public ModelAndView goProductStock(
			@PathVariable("productId") Long productId,
			@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "9") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {
		
		Page<ProductItem> itemsPage = 
				productItemService.findAllPaginatedForProductId(
						productId, pageNumber, pageSize, sortBy);
				
		model.addAttribute("items", itemsPage.getContent());
		model.addAttribute("currentPage",  itemsPage.getNumber());
		model.addAttribute("numberOfPages", itemsPage.getTotalPages());
		model.addAttribute("productId", productId);
		model.addAttribute("productStock", productItemService.getStockForProductId(productId));
		
		return new ModelAndView("product_stock");
	}
	
	@RequestMapping(value="/{productId}/add_item", method = RequestMethod.POST)
	public ModelAndView addProductItem(
			ModelMap model,
			@ModelAttribute ProductItem item,
			@PathVariable("productId") Long productId) {
		
		productItemService.save(item, productId);
		
		return new ModelAndView(
				"redirect:/products/" + productId + "/product_items?item_added=true");
	}

}
