package com.microservices.employee.service;

import java.util.List;

import com.microservices.employee.exception.EmployeeException;
import com.microservices.employee.model.Employee;

public interface EmployeeServiceIF {
	public List<Employee> getEmployees() throws EmployeeException;
	public Object getEmployee(Long employeeId) throws EmployeeException;
	public Object saveEmployee(Employee employee) throws EmployeeException;
	public Object updateEmployee(Employee employee) throws EmployeeException;
	public Object deleteEmployee(Long employeeId) throws EmployeeException;
}
