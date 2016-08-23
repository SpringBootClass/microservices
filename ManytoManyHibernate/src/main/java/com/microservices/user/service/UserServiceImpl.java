package com.microservices.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.user.dao.UserDaoIF;
import com.microservices.user.exception.UserException;
import com.microservices.user.model.User;

@Service
public class UserServiceImpl implements UserServiceIF {
	
	@Autowired
	private UserDaoIF userDaoIF;

	@Override
	public List<User> getUsers() throws UserException {
		return userDaoIF.getUsers();
	}

	@Override
	public Object getUser(Long userId) throws UserException {
		return userDaoIF.getUser(userId);
	}

	public UserDaoIF getUserDaoIF() {
		return userDaoIF;
	}

	public void setUserDaoIF(UserDaoIF userDaoIF) {
		this.userDaoIF = userDaoIF;
	}

	@Override
	public Object saveUser(User user) throws UserException {
		return userDaoIF.saveUser(user);
	}

	@Override
	public Object updateUser(User user) throws UserException {
		return userDaoIF.updateUser(user);
	}

	@Override
	public Object deleteUser(Long userId) throws UserException {
		return userDaoIF.deleteUser(userId);
	}
	
}
