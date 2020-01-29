package com.online.store.controllers;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.online.store.data.entities.Product;
import com.online.store.data.entities.ProductImage;
import com.online.store.services.ProductService;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("")
	public ModelAndView showProducts(
			@RequestParam(required = false, value = "page_number") Integer requestedPage,
			ModelMap model) {
		
		int pageNumber = requestedPage != null ? requestedPage : 0;
		Page<Product> productsPage = productService.findAllPaginated(pageNumber);
		
		model.addAttribute("products", productsPage.getContent());
		model.addAttribute("numberOfPages", productsPage.getTotalPages());
		model.addAttribute("currentPage", productsPage.getNumber());
				
		return new ModelAndView("products", model);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView goAddProduct(ModelMap model) {
		model.addAttribute("product", new Product()); 
		return new ModelAndView("add_product", model);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView newProduct(
			@ModelAttribute("product") Product product,
			@RequestParam(value = "image", required = false) CommonsMultipartFile[] imagesUpload, // more than one image can be uploaded at a time.
			@RequestParam(value = "main_image") CommonsMultipartFile mainImage,
			ModelMap model) {
		
		// Add main image which goes in the products table. Additional images 
		// are stored in the product_images table.
		if(mainImage != null && !mainImage.isEmpty()) {
			product.setMainImage(mainImage.getBytes());
		}
		
		// Add and save additional images in the product_images table.
		Set<ProductImage> images = new HashSet<>();
		if(imagesUpload != null && imagesUpload.length > 0) {
			for(CommonsMultipartFile imageUpload: imagesUpload) {
				// If no image was chosen, jet imagesUpload will contain an empty string.
				if(!imageUpload.isEmpty()) {
					ProductImage image = new ProductImage();
					image.setImage(imageUpload.getBytes());
					image.setProduct(product);
					
					images.add(image);
				}
			}
		}
		
		if(!images.isEmpty())
			product.setImages(images);
		
		productService.save(product);
		
		return new ModelAndView("/add_product", model);
	}
	
	@RequestMapping(value = "/{productId}")
	public ModelAndView productDetails(
			@PathVariable("productId") Long productId,
			ModelMap model) {
		
		Product product = productService.findProductByIdWithImages(productId);
		
		String[] images = product.getImages().stream()
				.map(productImage -> {
					byte[] imageByte = productImage.getImage();
					return Base64.getEncoder().encodeToString(imageByte);
				}).toArray(String[]::new);
		
		model.addAttribute("product", product);
		model.addAttribute("productImages", images);
		
		return new ModelAndView("/product_details", model);
	}

}
