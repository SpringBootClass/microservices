package com.microservices.user.controller;

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

import com.microservices.result.Result;
import com.microservices.user.model.User;
import com.microservices.user.service.UserServiceIF;

@RestController
@RequestMapping(path = "/user/v1")
public class UserController {

	
	
	@Autowired
	private UserServiceIF userServiceIF;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getUsers() {

		HttpStatus statusCode = null;
		Result result = new Result();
		List<User> userList = null;
		try {
			userList = userServiceIF.getUsers();
			if (userList.isEmpty()) {
				result.setObject(userList);
			} else {
				result.setObject(userList);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(userList);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getUser(@PathVariable("userId") String userId) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Object user = null;
		try {
			user = userServiceIF.getUser(Long.parseLong(userId));
			if (user == null) {
				result.setObject(user);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				result.setObject(user);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(user);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	public UserServiceIF getUserServiceIF() {
		return userServiceIF;
	}

	public void setUserServiceIF(UserServiceIF userServiceIF) {
		this.userServiceIF = userServiceIF;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> saveUser(@RequestBody User user) {

		HttpStatus statusCode = null;
		Result result = new Result();
		try {
			if (user == null) {
				result.setObject(user);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				logger.info("user.getId() else: "+user.getId());
				userServiceIF.saveUser(user);
				result.setObject(user);
				statusCode = HttpStatus.OK;
			}
		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(user);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}
		return new ResponseEntity<Result>(result, statusCode);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> updateUser(@RequestBody User user) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Object user1 = null;
		try {
			if (user == null) {
				result.setObject(user);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				user1 = userServiceIF.updateUser(user);
				if(user1 == null){
					result.setObject(user1);
					statusCode = HttpStatus.NO_CONTENT;
				}
				else{
				result.setObject(user1);
				statusCode = HttpStatus.OK;
				}
			}
		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(user);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> deleteUser(@PathVariable("userId") String userId) {
		HttpStatus statusCode = null;
		Result result = new Result();
		Object user = null;
		try {
			user = userServiceIF.deleteUser(Long.parseLong(userId));
			if (user == null) {
				result.setObject(user);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				result.setObject(user);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(user);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

}
