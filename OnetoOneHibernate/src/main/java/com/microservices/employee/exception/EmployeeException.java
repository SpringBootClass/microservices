package com.microservices.employee.exception;

public class EmployeeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EmployeeException(String message){
		super(message);
	}
	public EmployeeException(Exception exception){
		super(exception);
	}
	public EmployeeException(String message,Exception exception){
		super(message,exception);
	}
	
}
