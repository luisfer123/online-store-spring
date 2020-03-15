package com.online.store.exceptions;

public class ShoppingCartEmptyException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ShoppingCartEmptyException() {
		super("There are no products in the shopping cart!");
	}

}
