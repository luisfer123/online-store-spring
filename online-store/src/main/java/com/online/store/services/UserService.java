package com.online.store.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.store.data.entities.Authority;
import com.online.store.data.entities.User;
import com.online.store.repositories.AuthorityRepository;
import com.online.store.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Transactional(readOnly = true)
	public List<User> findAll() {
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	@Transactional(readOnly = true)
	public User findByUsernameWithAuthorities(String username) {
		Optional<User> optional = userRepository.findByUsername(username);
		
		if(!optional.isPresent())
			throw new UsernameNotFoundException("There is not user with the given username!");
		
		User user = optional.get();
		
		Set<Authority> userAuthorities = authorityRepository.findUserAuthoritiesByUsername(username);
		user.setAuthorities(userAuthorities);
		
		return user;
	}

}
