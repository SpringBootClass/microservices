package com.microservices.employee.address.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.employee.address.model.EmployeeAddress;
import com.microservices.employee.address.service.EmployeeAddressServiceIF;
import com.microservices.result.Result;

@RestController
@RequestMapping(path = "/employeeaddress/v1")
public class EmployeeAddressController {

	
	
	@Autowired
	private EmployeeAddressServiceIF employeeAddressServiceIF;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/employeeaddresses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getEmployeeAddresses() {

		HttpStatus statusCode = null;
		Result result = new Result();
		List<EmployeeAddress> employeeAddressList = null;
		try {
			employeeAddressList = employeeAddressServiceIF.getEmployeeAddresses();
			if (employeeAddressList.isEmpty()) {
				result.setObject(employeeAddressList);
			} else {
				result.setObject(employeeAddressList);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employeeAddressList);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{employeeAddressId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getEmployeeAddress(@PathVariable("employeeAddressId") String employeeAddressId) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Object employeeAddress = null;
		try {
			employeeAddress = employeeAddressServiceIF.getEmployeeAddress(Long.parseLong(employeeAddressId));
			if (employeeAddress == null) {
				result.setObject(employeeAddress);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				result.setObject(employeeAddress);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employeeAddress);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> saveEmployeeAddress(@RequestBody EmployeeAddress employeeAddress) {

		HttpStatus statusCode = null;
		Result result = new Result();
		try {
			
			if (employeeAddress == null) {
				result.setObject(employeeAddress);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				employeeAddressServiceIF.saveEmployeeAddress(employeeAddress);
				result.setObject(employeeAddress);
				statusCode = HttpStatus.OK;
			}
		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employeeAddress);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> updateEmployeeAddress(@RequestBody EmployeeAddress employeeAddress) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Object employeeAddress1 = null;
		try {
			if (employeeAddress == null) {
				result.setObject(employeeAddress);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				employeeAddress1 = employeeAddressServiceIF.updateEmployeeAddress(employeeAddress);
				if(employeeAddress1 == null){
					result.setObject(employeeAddress1);
					statusCode = HttpStatus.NO_CONTENT;
				}
				else{
				result.setObject(employeeAddress1);
				statusCode = HttpStatus.OK;
				}
			}
		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employeeAddress);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{employeeAddressId}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> deleteEmployeeAddress(@PathVariable("employeeAddressId") String employeeAddressId) {
		HttpStatus statusCode = null;
		Result result = new Result();
		Object employeeAddress = null;
		try {
			employeeAddress = employeeAddressServiceIF.deleteEmployeeAddress(Long.parseLong(employeeAddressId));
			if (employeeAddress == null) {
				result.setObject(employeeAddress);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				result.setObject(employeeAddress);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employeeAddress);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}
	
	
	public EmployeeAddressServiceIF getEmployeeAddressServiceIF() {
		return employeeAddressServiceIF;
	}

	public void setEmployeeAddressServiceIF(EmployeeAddressServiceIF employeeAddressServiceIF) {
		this.employeeAddressServiceIF = employeeAddressServiceIF;
	}

	
}
