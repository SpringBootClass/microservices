package com.microservices.employee.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.employee.exception.EmployeeException;
import com.microservices.employee.model.Employee;

@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class EmployeeDaoImpl implements EmployeeDaoIF {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployees() throws EmployeeException {
		Session session = null;
		List<Employee> employeeList = null;

		try {
			session = sessionFactory.getCurrentSession();
			employeeList = session.createQuery("from Employee").list();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
		}
		return employeeList;
	}

	@Override
	public Object getEmployee(Long employeeId) throws EmployeeException {
		Session session = null;
		Object employee = null;

		try {
			session = sessionFactory.getCurrentSession();
			employee = session.get(Employee.class,employeeId);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {

		}
		return employee;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Object saveEmployee(Employee employee) throws EmployeeException {
		Session session = null;
		Long id =0L;
		try {
			session = sessionFactory.getCurrentSession();
			id = (Long)session.save(employee);
			return getEmployee(id);
		} catch (Exception ex) {
			throw ex;
		} finally {
		}
	}

	@Override
	public Object updateEmployee(Employee employee) throws EmployeeException {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.update(employee);
			return getEmployee(employee.getEmployeeId());
		} catch (Exception ex) {
			throw ex;
		} finally {
		}
	}

	@Override
	public Object deleteEmployee(Long employeeId) throws EmployeeException {
		Session session = null;
		Object employee=null;
		try {
			employee = getEmployee(employeeId);
			session = sessionFactory.getCurrentSession();
			session.delete(employee);
			return employee;
		} catch (Exception ex) {
			throw ex;
		} finally {
		}
	}
	
}
