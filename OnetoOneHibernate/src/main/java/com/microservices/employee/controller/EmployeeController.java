package com.microservices.employee.controller;

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

import com.microservices.employee.model.Employee;
import com.microservices.employee.service.EmployeeServiceIF;
import com.microservices.result.Result;

@RestController
@RequestMapping(path = "/employee/v1")
public class EmployeeController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EmployeeServiceIF employeeServiceIF;
	
	public EmployeeServiceIF getEmployeeServiceIF() {
		return employeeServiceIF;
	}

	public void setEmployeeServiceIF(EmployeeServiceIF employeeServiceIF) {
		this.employeeServiceIF = employeeServiceIF;
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getEmployees() {

		HttpStatus statusCode = null;
		Result result = new Result();
		List<Employee> employeeList = null;
		try {
			employeeList = employeeServiceIF.getEmployees();
			if (employeeList.isEmpty()) {
				result.setObject(employeeList);
			} else {
				result.setObject(employeeList);
				statusCode = HttpStatus.OK;

			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employeeList);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getEmployee(@PathVariable("employeeId") String employeeId) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Object employee = null;
		try {
			employee = employeeServiceIF.getEmployee(Long.parseLong(employeeId));
			if (employee == null) {
				result.setObject(employee);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				result.setObject(employee);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employee);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> saveEmployee(@RequestBody Employee employee) {

		HttpStatus statusCode = null;
		Result result = new Result();
		try {
			
			if (employee == null) {
				result.setObject(employee);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				employeeServiceIF.saveEmployee(employee);
				result.setObject(employee);
				statusCode = HttpStatus.OK;
			}
		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employee);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> updateEmployee(@RequestBody Employee employee) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Object employee1 = null;
		try {
			if (employee == null) {
				result.setObject(employee);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				employee1 = employeeServiceIF.updateEmployee(employee);
				if(employee1 == null){
					result.setObject(employee1);
					statusCode = HttpStatus.NO_CONTENT;
				}
				else{
				result.setObject(employee1);
				statusCode = HttpStatus.OK;
				}
			}
		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employee);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> deleteEmployee(@PathVariable("employeeId") String employeeId) {
		HttpStatus statusCode = null;
		Result result = new Result();
		Object employee = null;
		try {
			employee = employeeServiceIF.deleteEmployee(Long.parseLong(employeeId));
			if (employee == null) {
				result.setObject(employee);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				result.setObject(employee);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employee);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	
}
