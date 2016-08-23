package com.microservices.group.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.group.exception.GroupException;
import com.microservices.group.model.Group;

@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class GroupDaoImpl implements GroupDaoIF {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getGroups() throws GroupException {
		Session session = null;
		List<Group> groupList = null;

		try {
			session = sessionFactory.getCurrentSession();
			groupList = session.createQuery("from Group").list();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			
		}
		return groupList;
	}

	@Override
	public Object getGroup(Long groupId) throws GroupException {
		Session session = null;
		Object group = null;

		try {
			session = sessionFactory.getCurrentSession();
			group = session.get(Group.class,groupId);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {

		}
		return group;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Object saveGroup(Group group) throws GroupException {
		Session session = null;
		Long id = 0L;
		try {
			session = sessionFactory.getCurrentSession();
			id = (Long) session.save(group);
			return getGroup(id);
		} catch (Exception ex) {
			throw ex;
		} finally {
		}
	}

	@Override
	public Object updateGroup(Group group) throws GroupException {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.update(group);
			return getGroup(group.getId());
		} catch (Exception ex) {
			throw ex;
		} finally {
		}
	}

	@Override
	public Object deleteGroup(Long groupId) throws GroupException {
		Session session = null;
		Object group = null;
		try {
			group = getGroup(groupId);
			session = sessionFactory.getCurrentSession();
			session.delete(groupId);
			return group;
		} catch (Exception ex) {
			throw ex;
		} finally {
		}
	}

	
}
