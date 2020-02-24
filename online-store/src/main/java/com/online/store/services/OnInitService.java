package com.online.store.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.store.data.entities.Authority;
import com.online.store.data.entities.Product;
import com.online.store.data.entities.User;
import com.online.store.repositories.AuthorityRepository;
import com.online.store.repositories.ProductRepository;
import com.online.store.repositories.UserRepository;

@Service
@Transactional
public class OnInitService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void onInit() {

		createAuthorities();
		createUsers();
		createProducts();

	}

	public void createAuthorities() {

		Optional<Authority> oAdminRole = authRepo.findByAuthority("ROLE_ADMIN");
		if (!oAdminRole.isPresent()) {
			Authority roleAdmin = new Authority();
			roleAdmin.setAuthority("ROLE_ADMIN");
			authRepo.save(roleAdmin);
		}
		
		Optional<Authority> oUserRole = authRepo.findByAuthority("ROLE_USER");
		if (!oUserRole.isPresent()) {
			Authority roleUser = new Authority();
			roleUser.setAuthority("ROLE_USER");
			authRepo.save(roleUser);
		}
	}

	public void createUsers() {

		Optional<User> oAdmin = userRepository.findByUsername("admin");
		if (!oAdmin.isPresent()) {
			User admin = new User();
			admin.setUsername("admin");
			admin.setEmail("adminmail@gmail.com");
			admin.setPassword(passwordEncoder.encode("password"));

			Authority adminAuthority = authRepo.findByAuthority("ROLE_ADMIN").get();
			Authority userAuthority = authRepo.findByAuthority("ROLE_USER").get();
			adminAuthority.getUsers().add(admin);
			userAuthority.getUsers().add(admin);
			Set<Authority> adminAuthorities = new HashSet<Authority>();
			adminAuthorities.add(adminAuthority);
			adminAuthorities.add(userAuthority);

			admin.setAuthorities(adminAuthorities);
			
			final Resource profileImageResource = 
					resourceLoader.getResource("classpath:static/pictures/user.png");
			try {
				byte[] profileImage = IOUtils.toByteArray(profileImageResource.getInputStream());
				admin.setProfileImage(profileImage);
			} catch(IOException e) {
				e.printStackTrace();
			}

			userRepository.save(admin);
		}

		Optional<User> oUser = userRepository.findByUsername("user");
		if (!oUser.isPresent()) {
			User user = new User();
			user.setUsername("user");
			user.setEmail("usermail@gmail.com");
			user.setPassword(passwordEncoder.encode("password"));

			Authority userAuthority = authRepo.findByAuthority("ROLE_USER").get();
			userAuthority.getUsers().add(user);
			Set<Authority> userAuthorities = new HashSet<Authority>();
			userAuthorities.add(userAuthority);
			
			user.setAuthorities(userAuthorities);
			
			final Resource profileImageResource = 
					resourceLoader.getResource("classpath:static/pictures/user.png");
			try {
				byte[] profileImage = IOUtils.toByteArray(profileImageResource.getInputStream());
				user.setProfileImage(profileImage);
			} catch(IOException e) {
				e.printStackTrace();
			}

			userRepository.save(user);
		}
	}

	public void createProducts() {
		
		createProduct("t-shirt", "productTShirt.jpg");
		createProduct("shoes", "productShoes.jpg");
		for(int i = 0; i < 99; i++) {
			createProduct("product-"+i, "genericProduct.jpg");
		}
	}
	
	public void createProduct(String name, String imageName) {
		Optional<Product> oShoes = productRepo.findByName(name);
		if (!oShoes.isPresent()) {

			Product shoes = new Product();
			shoes.setName(name);

			final Resource imageResource = 
					resourceLoader.getResource("classpath:static/pictures/" + imageName);
			try {
				shoes.setMainImage(IOUtils.toByteArray(imageResource.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}

			productRepo.save(shoes);
		}
	}

}
