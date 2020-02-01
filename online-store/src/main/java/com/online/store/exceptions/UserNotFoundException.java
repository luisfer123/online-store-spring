package com.online.store.exceptions;

public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super("User was not found in the database");
	}

}
