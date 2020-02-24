package com.online.store.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.store.data.entities.Authority;
import com.online.store.data.entities.User;
import com.online.store.exceptions.UserNotFoundException;
import com.online.store.repositories.AuthorityRepository;
import com.online.store.repositories.UserRepository;
import com.online.store.security.UtilSecurity;
import com.online.store.services.interfaces.IUserService;

@Service
public class UserService implements IUserService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public User findById(Long id) {
		
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException();
		
		return user.get();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<User> findAllPaginated(int pageNumber) {
		
		int pageSize = 9;
		
		PageRequest pageResquested = PageRequest.of(pageNumber, pageSize);
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
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		
		Optional<User> user = userRepository.findByUsername(username);
		
		if(!user.isPresent())
			throw new UsernameNotFoundException("User does not exist!");
		
		return user.get();
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long userId) {
		
		Optional<User> optional = userRepository.findById(userId);
		
		if(!optional.isPresent())
			throw new UserNotFoundException();
		
		userRepository.delete(optional.get());
		
	}
	
	@Override
	@Transactional
	public void registerNewUser(User user) {
		
		// We need to store a encrypted version of the password
		user.setPassword(encoder.encode(user.getPassword()));
		
		// Assign the corresponding authorities to the new user to
		// interact with spring security.
		Set<Authority> authorities = new HashSet<>();
		authorities.add(authorityRepository.findByAuthority("ROLE_USER").get());
		user.setAuthorities(authorities);
		
		// Save the new user in the database
		userRepository.save(user);
		
		// Programmatically authenticate new user in spring security once it is created.
		Authentication authenticationToken = 
				new UsernamePasswordAuthenticationToken(
						UtilSecurity.buildUserDetails(user),
						user.getPassword(),
						UtilSecurity.getAuthorityList(user.getAuthorities())
				);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		
	}

}
