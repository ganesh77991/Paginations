package com.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.app.dao.IEmployeeDao;
import com.app.model.Employee;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao {

	@Autowired
	private HibernateTemplate ht;

	@Override
	public Integer saveEmployee(Employee employee) {

		System.out.println(employee);
		System.out.println("EmployeeDaoImpl.saveEmployee()");
		return (Integer) ht.save(employee);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {

		List<Employee> list = ht.loadAll(Employee.class);

		return list;

	}

}
