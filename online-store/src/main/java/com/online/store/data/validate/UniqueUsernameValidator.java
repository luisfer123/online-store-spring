package com.online.store.data.validate;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.online.store.data.entities.User;
import com.online.store.repositories.UserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		
		if(userRepository == null)
			return true;

		Optional<User> optional = userRepository.findByUsername(username);
		return !optional.isPresent();
		
	}

}
