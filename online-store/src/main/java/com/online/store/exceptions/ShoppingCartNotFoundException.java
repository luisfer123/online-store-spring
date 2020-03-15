package com.online.store.exceptions;

public class ShoppingCartNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ShoppingCartNotFoundException() {
		super("Such Cart does not Exists!");
	}
}
