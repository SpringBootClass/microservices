package com.microservices.user.service;

import java.util.List;

import com.microservices.user.exception.UserException;
import com.microservices.user.model.User;

public interface UserServiceIF {
	public List<User> getUsers() throws UserException;
	public Object getUser(Long userId) throws UserException;
	public Object saveUser(User user) throws UserException;
	public Object updateUser(User user) throws UserException;
	public Object deleteUser(Long userId) throws UserException;
}
