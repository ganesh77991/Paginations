package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IEmployeeDao;
import com.app.model.Employee;
import com.app.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	IEmployeeDao dao;

	@Override
	@Transactional
	public Integer saveEmployee(Employee employee) {
		return dao.saveEmployee(employee);
	}

	@Override
	public int getTotalCount() {
		return dao.getTotalCount();
	}

	@Override
	public List<Employee> getAllEmployees(Integer pageNo, int pages) {
		return dao.getAllEmployees(pageNo, pages);
	}

}
