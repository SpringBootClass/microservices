package com.microservices.cart.exception;

public class CartException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CartException(String message){
		super(message);
	}
	public CartException(Exception exception){
		super(exception);
	}
	public CartException(String message,Exception exception){
		super(message,exception);
	}
	
}
