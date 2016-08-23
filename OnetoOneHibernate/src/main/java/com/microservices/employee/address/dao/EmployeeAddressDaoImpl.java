package com.microservices.employee.address.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.employee.address.exception.EmployeeAddressException;
import com.microservices.employee.address.model.EmployeeAddress;

@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class EmployeeAddressDaoImpl implements EmployeeAddressDaoIF {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeAddress> getEmployeeAddresses() throws EmployeeAddressException {
		Session session = null;
		List<EmployeeAddress> employeeAddressesList = null;

		try {
			session = sessionFactory.getCurrentSession();
			employeeAddressesList = session.createQuery("from EmployeeAddress").list();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			
		}
		return employeeAddressesList;
	}

	@Override
	public Object getEmployeeAddress(Long employeeAddressId) throws EmployeeAddressException {
		Session session = null;
		Object employeeAddress = null;

		try {
			session = sessionFactory.getCurrentSession();
			employeeAddress = session.get(EmployeeAddress.class,employeeAddressId);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {

		}
		return employeeAddress;
	}

	@Override
	public Object saveEmployeeAddress(EmployeeAddress employeeAddress) throws EmployeeAddressException {
		Session session = null;
		Long id = 0L;
		try {
			session = sessionFactory.getCurrentSession();
			id = (Long)session.save(employeeAddress);
			return getEmployeeAddress(id);
		} catch (Exception ex) {
			throw ex;
		} finally {
		}
	}

	@Override
	public Object updateEmployeeAddress(EmployeeAddress employeeAddress) throws EmployeeAddressException {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.update(employeeAddress);
			return getEmployeeAddress(employeeAddress.getEmployeeId());
		} catch (Exception ex) {
			throw ex;
		} finally {
		}
	}

	@Override
	
	public Object deleteEmployeeAddress(Long employeeAddressId) throws EmployeeAddressException{
		Session session = null;
		Object employeeAddress=null;
		try {
			employeeAddress = getEmployeeAddress(employeeAddressId);
			session = sessionFactory.getCurrentSession();
			session.delete(employeeAddressId);
			return employeeAddress;
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

	
}
