package com.online.store.exceptions;

public class ProductNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException() {
		super("The product required was not found!");
	}

}
