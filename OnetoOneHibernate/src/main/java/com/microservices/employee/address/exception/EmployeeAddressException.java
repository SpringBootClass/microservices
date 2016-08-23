package com.microservices.employee.address.exception;



public class EmployeeAddressException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EmployeeAddressException(String message){
		super(message);
	}
	public EmployeeAddressException(Exception exception){
		super(exception);
	}
	public EmployeeAddressException(String message,Exception exception){
		super(message,exception);
	}
	
}
