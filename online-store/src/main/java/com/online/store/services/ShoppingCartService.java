package com.online.store.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.store.data.entities.Product;
import com.online.store.data.entities.ShoppingCart;
import com.online.store.data.entities.User;
import com.online.store.exceptions.ProductNotFoundException;
import com.online.store.exceptions.ShoppingCartEmptyException;
import com.online.store.exceptions.ShoppingCartNotFoundException;
import com.online.store.repositories.ShoppingCartRepository;
import com.online.store.repositories.UserRepository;
import com.online.store.security.UtilSecurity;

@Service
public class ShoppingCartService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ShoppingCartRepository cartRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	/**
	 * Find the cart of the currently logged in user. Uses the SecurityContext
	 * to get the logged in user.
	 * 
	 * @return Cart of the currently logged in user.
	 */
	@Transactional
	public Optional<ShoppingCart> findByUser() {
		
		User user = 
				userRepo
					.findByUsername(UtilSecurity.getPrincipalUsername())
					.get();
		
		return cartRepo.findByUser(user);	
	}
	
	@Transactional
	public void save(ShoppingCart cart) {
		cartRepo.save(cart);
	}
	
	/**
	 * Saves the Product in the ShoppingCart of the currently logged in user.
	 * If the currently logged in user does not have a cart, it creates one and
	 * then saves the product there.
	 * 
	 * @param productId - id of the product to be saved in the ShoppingCart
	 */
	@Transactional
	public void saveProductInLoggedInUserCart(Long productId) {
		
		Product product = productService.findById(productId);
		User user = userService
				.findByUsername(UtilSecurity.getPrincipalUsername());
		
		if(user.getShoppingCart() == null) {
			ShoppingCart cart = new ShoppingCart();
			HashSet<Product> cartProducts = new HashSet<>();
			cartProducts.add(product);
			cart.setProducts(cartProducts);
			
			user.setShoppingCart(cart);
			cart.setUser(user);
			
			this.save(cart);
		} else {
			ShoppingCart cart = user.getShoppingCart();
			Set<Product> cartProducts = cart.getProducts();
			
			if(cartProducts == null) 
				cart.setProducts(new HashSet<Product>());
			
			if(cartProducts.contains(product))
				System.out.println("Product is already in cart!");
			
			cart.getProducts().add(product);
			this.save(cart);
		}
	}
	
	@Transactional
	public ShoppingCart findById(Long id) throws ShoppingCartNotFoundException {
		Optional<ShoppingCart> oCart = cartRepo.findById(id);
		
		if(!oCart.isPresent())
			throw new ShoppingCartNotFoundException();
		
		return oCart.get();
	}
	
	/**
	 * Deletes the Product with the given productId from the ShoppingCart
	 * of the currently logged in user.
	 * 
	 * @param productId - Id of the product to be deleted from the currently logged in user's ShoppingCart.
	 * @return User's Shopping cart with out the deleted product.
	 * @throws ShoppingCartNotFoundException
	 * @throws ProductNotFoundException
	 */
	@Transactional
	public ShoppingCart deleteProductById(Long productId) 
			throws ShoppingCartNotFoundException, ProductNotFoundException, ShoppingCartEmptyException {
		
		Optional<ShoppingCart> oCart = this.findByUser();
		if(!oCart.isPresent())
			throw new UsernameNotFoundException("User not found!");
		
		ShoppingCart cart = oCart.get();
		Set<Product> cartProducts = cart.getProducts();
		
		if(cartProducts == null || cartProducts.isEmpty())
			throw new ShoppingCartEmptyException();
		
		Product productToDelete = null;
		for(Product product: cartProducts) {
			if(product.getId().equals(productId))
				productToDelete = product;
		}
		
		if(productToDelete == null)
			throw new ProductNotFoundException();
		
		cart.getProducts().remove(productToDelete);
		
		return cartRepo.save(cart);
				
	}

}
