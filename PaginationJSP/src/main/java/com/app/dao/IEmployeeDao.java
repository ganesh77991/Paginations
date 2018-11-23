package com.app.dao;

import java.util.List;

import com.app.model.Employee;

public interface IEmployeeDao {

	public Integer saveEmployee(Employee employee);
	
	public List<Employee> getAllEmployees();
}
