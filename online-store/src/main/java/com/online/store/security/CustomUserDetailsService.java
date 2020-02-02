package com.online.store.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.online.store.data.entities.User;
import com.online.store.services.UserService;

public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
		User user = userService.findByUsernameWithAuthorities(username);
		
		org.springframework.security.core.userdetails.User userService = 
				new org.springframework.security.core.userdetails.User(
						user.getUsername(),
						user.getPassword(),
						UtilSecurity.getAuthorityList(user.getAuthorities())
					);
				
		return userService;
	}

}
