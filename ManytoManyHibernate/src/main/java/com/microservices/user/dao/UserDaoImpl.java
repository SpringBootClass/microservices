package com.microservices.user.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.user.exception.UserException;
import com.microservices.user.model.User;

@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserDaoImpl implements UserDaoIF {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() throws UserException {
		Session session = null;
		List<User> userList = null;

		try {
			session = sessionFactory.getCurrentSession();
			userList = session.createQuery("from User").list();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			
		}
		return userList;
	}

	@Override
	public Object getUser(Long userId) throws UserException {
		Session session = null;
		Object user = null;

		try {
			session = sessionFactory.getCurrentSession();
			user = session.get(User.class,userId);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {

		}
		return user;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Object saveUser(User user) throws UserException {
		Session session = null;
		Long id = 0L;
		try {
			session = sessionFactory.getCurrentSession();
			id = (Long) session.save(user);
			return getUser(id);
		} catch (Exception ex) {
			throw ex;
		} finally {
		}
	}

	@Override
	public Object updateUser(User user) throws UserException {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.update(user);
			return getUser(user.getId());
		} catch (Exception ex) {
			throw ex;
		} finally {
		}
	}

	@Override
	public Object deleteUser(Long userId) throws UserException {
		Session session = null;
		Object user = null;
		try {
			user = getUser(userId);
			session = sessionFactory.getCurrentSession();
			session.delete(userId);
			return user;
		} catch (Exception ex) {
			throw ex;
		} finally {
		}
	}
	
}
