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
import com.online.store.exceptions.UserNotFoundException;
import com.online.store.repositories.AuthorityRepository;
import com.online.store.repositories.UserRepository;
import com.online.store.services.interfaces.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Page<User> findAllPaginated(int pageNumber) {
		
		PageRequest pageResquested = PageRequest.of(pageNumber, 9);
		Page<User> usersPage = userRepository.findAll(pageResquested);
		
		return usersPage;
	}
	
	@Override
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

	@Override
	@Transactional(readOnly = false)
	public void delete(Long userId) {
		
		Optional<User> optional = userRepository.findById(userId);
		
		if(!optional.isPresent())
			throw new UserNotFoundException();
		
		userRepository.delete(optional.get());
		
	}

}
