package com.online.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.store.data.entities.User;
import com.online.store.repositories.UserRepository;
import com.online.store.services.interfaces.IUserService;

@Service
@Transactional
public class UpdateUserProfileService {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	public void updateFirstName(String newFirstName) {
		
		Object principal = 
				SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		
		if(principal instanceof UserDetails)
			username = ((UserDetails) principal).getUsername();
		else
			username = principal.toString();
		
		User user = userService.findByUsername(username);
		user.setFirstName(newFirstName);
		userRepository.save(user);
	}

}
