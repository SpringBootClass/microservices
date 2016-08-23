package com.microservices.employee.address.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.employee.address.dao.EmployeeAddressDaoIF;
import com.microservices.employee.address.exception.EmployeeAddressException;
import com.microservices.employee.address.model.EmployeeAddress;

@Service
public class EmployeeAddressServiceImpl implements EmployeeAddressServiceIF {
	
	@Autowired
	private EmployeeAddressDaoIF employeeAddressDaoIF;

	@Override
	public List<EmployeeAddress> getEmployeeAddresses() throws EmployeeAddressException {
		return employeeAddressDaoIF.getEmployeeAddresses();
	}

	@Override
	public Object getEmployeeAddress(Long employeeId) throws EmployeeAddressException {
		return employeeAddressDaoIF.getEmployeeAddress(employeeId);
	}

	public EmployeeAddressDaoIF getEmployeeAddressDaoIF() {
		return employeeAddressDaoIF;
	}

	public void setEmployeeAddressDaoIF(EmployeeAddressDaoIF employeeAddressDaoIF) {
		this.employeeAddressDaoIF = employeeAddressDaoIF;
	}

	@Override
	public Object saveEmployeeAddress(EmployeeAddress employeeAddress) throws EmployeeAddressException {
		return employeeAddressDaoIF.saveEmployeeAddress(employeeAddress);
	}

	@Override
	public Object updateEmployeeAddress(EmployeeAddress employeeAddress) throws EmployeeAddressException {
		return employeeAddressDaoIF.updateEmployeeAddress(employeeAddress);
	}

	@Override
	public Object deleteEmployeeAddress(Long employeeAddressId) throws EmployeeAddressException {
		return employeeAddressDaoIF.deleteEmployeeAddress(employeeAddressId);
	}


	
}
