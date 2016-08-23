package com.microservices.employee.address.service;

import java.util.List;

import com.microservices.employee.address.exception.EmployeeAddressException;
import com.microservices.employee.address.model.EmployeeAddress;

public interface EmployeeAddressServiceIF {
	public List<EmployeeAddress> getEmployeeAddresses() throws EmployeeAddressException;
	public Object getEmployeeAddress(Long employeeId) throws EmployeeAddressException;
	public Object saveEmployeeAddress(EmployeeAddress employeeAddress) throws EmployeeAddressException;
	public Object updateEmployeeAddress(EmployeeAddress employeeAddress) throws EmployeeAddressException;
	public Object deleteEmployeeAddress(Long employeeAddressId) throws EmployeeAddressException;
}
