package com.online.store.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	public Page<User> findAllPaginated(int pageNumber) {
		
		PageRequest pageResquested = PageRequest.of(pageNumber, 9);
		Page<User> usersPage = userRepository.findAll(pageResquested);
		
		return usersPage;
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
