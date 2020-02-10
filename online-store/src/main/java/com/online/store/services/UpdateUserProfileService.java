package com.online.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.store.data.entities.User;
import com.online.store.repositories.UserRepository;
import com.online.store.security.UtilSecurity;
import com.online.store.services.interfaces.IUpdateUserProfileService;
import com.online.store.services.interfaces.IUserService;

@Service
@Transactional
public class UpdateUserProfileService implements IUpdateUserProfileService {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void updateUserStringFields(String newFieldValue, String fieldToUpdate) {
		
		User user = userService.findByUsername(
				UtilSecurity.getPrincipalUsername());
		
		switch(fieldToUpdate) {
			case "first_name":
				user.setFirstName(newFieldValue);
				break;
			case "last_name":
				user.setLastName(newFieldValue);
				break;
			case "email":
				user.setEmail(newFieldValue);
				break;
		}
		
		userRepository.save(user);
	}
	
	@Override
	public void updateUserPassword(String newPassword) {
		
	}
	
	@Override
	public void updateUserProfileImage(byte[] newImage) {
		User user = userService.findByUsername(
				UtilSecurity.getPrincipalUsername());
		
		user.setProfileImage(newImage);
		
		userRepository.save(user);
	}

}
