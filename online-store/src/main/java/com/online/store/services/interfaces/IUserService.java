package com.online.store.services.interfaces;

import org.springframework.data.domain.Page;

import com.online.store.data.entities.User;

public interface IUserService {
	
	Page<User> findAllPaginated(int pageNumber);
	
	User findByUsernameWithAuthorities(String username);
	
	void delete(Long userId);

	void registerNewUser(User newUser);
	
	User findByUsername(String username);

	User findById(Long id);

}
