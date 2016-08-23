package com.microservices.group.exception;



public class GroupException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GroupException(String message){
		super(message);
	}
	public GroupException(Exception exception){
		super(exception);
	}
	public GroupException(String message,Exception exception){
		super(message,exception);
	}
	
}
