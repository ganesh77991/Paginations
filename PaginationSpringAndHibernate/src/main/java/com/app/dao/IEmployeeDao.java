package com.app.dao;

import java.util.List;

import com.app.model.Employee;

public interface IEmployeeDao {

	public Integer saveEmployee(Employee employee);
	
	public int getTotalCount();
	public List<Employee> getAllEmployees(Integer pageNo, int pages);
	
}
