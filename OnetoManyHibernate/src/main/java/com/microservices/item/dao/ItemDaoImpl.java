package com.microservices.item.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.item.exception.ItemException;
import com.microservices.item.model.Item;

@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class ItemDaoImpl implements ItemDaoIF {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getItems() throws ItemException {
		Session session = null;
		List<Item> itemList = null;
		try {
			session = sessionFactory.getCurrentSession();
			itemList = session.createQuery("from Item").list();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
		}
		return itemList;
	}

	@Override
	public Object getItem(Long itemId) throws ItemException {
		Session session = null;
		Object employee = null;
		try {
			session = sessionFactory.getCurrentSession();
			employee = session.get(Item.class,itemId);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {

		}
		return employee;
	}
	
	@Override
	public Object saveItem(Item item) throws ItemException {
		Session session = null;
		Long id = 0L;
		try {
			session = sessionFactory.getCurrentSession();
			id = (Long)session.save(item);
			return getItem(id);
		} catch (Exception ex) {
			throw ex;
		} finally {
		}
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Object updateItem(Item item) throws ItemException {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.update(item);
			return getItem(item.getId());
		} catch (Exception ex) {
			throw ex;
		} finally {
		}
	}

	@Override
	public Object deleteItem(Long itemId) throws ItemException {
		Session session = null;
		Object item = null;
		try {
			item = getItem(itemId);
			session = sessionFactory.getCurrentSession();
			session.delete(itemId);
			return item;
		} catch (Exception ex) {
			throw ex;
		} finally {
		}
	}

	
}
