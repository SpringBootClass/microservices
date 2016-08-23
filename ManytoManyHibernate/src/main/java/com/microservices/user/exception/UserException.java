package com.microservices.user.exception;



public class UserException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserException(String message){
		super(message);
	}
	public UserException(Exception exception){
		super(exception);
	}
	public UserException(String message,Exception exception){
		super(message,exception);
	}
	
}
