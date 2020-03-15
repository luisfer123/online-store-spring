package com.online.store.services.interfaces;

import org.springframework.data.domain.Page;

import com.online.store.data.entities.User;

public interface IUserService {
	
	Page<User> findAllPaginated(int pageNumber, int pageSize, String sortBy);
	
	/**
	 * 
	 * 
	 * @param username The username of the user we want to fetch from the database.
	 * @return User entity with a set of the User's authorities.
	 * @exception UsernameNotFoundException if the provided username does not belong to any user stored in the database.
	 */
	User findByUsernameWithAuthorities(String username);
	
	void delete(Long userId);

	void registerNewUser(User newUser);
	
	User findByUsername(String username);

	User findById(Long id);
	
	void save(User user);

}
