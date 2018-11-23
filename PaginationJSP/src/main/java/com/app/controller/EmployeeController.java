package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.model.Employee;
import com.app.service.IEmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {

	private static int pageSize = 3;

	@Autowired
	private IEmployeeService service;

	// 1.to display Register p age
	@RequestMapping("register")
	public String showRegPage(ModelMap map) {

		map.addAttribute("employee", new Employee());

		return "EmployeeRegister";
	}

	// 2. on click Submit Button
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String saveData(@ModelAttribute Employee employee, ModelMap map) {

		Integer empId = service.saveEmployee(employee);
		String msg = "Employee '" + empId + "' saved";
		map.addAttribute("message", msg);
		// clean the form
		map.addAttribute("employee", new Employee());
		return "EmployeeRegister";
	}

	// 3. display data
	@RequestMapping("/all")
	public String displayData(ModelMap map) {

		// getting all emp records
		List<Employee> emps = service.getAllEmployees();

		// getting no of records from the table
		int totalCount = emps.size();

		// calculating the pages
		int pages = totalCount / pageSize;

		// make one extra page for remaining records
		if (totalCount % pageSize > 0) {
			pages++;
		}
		// send the start position to UI
		map.addAttribute("startPosition", "0");
		// send the page size to UI
		map.addAttribute("pageSize", pageSize);
		// send the number of pages
		map.addAttribute("pages", pages);
		// send the data to UI
		map.addAttribute("emps", emps);
		return "EmployeeData";
	}

	@RequestMapping("/page/{id}")
	public String displayPageData(@PathVariable("id") Integer pageNo, ModelMap map) {

		//getting all records from table
		List<Employee> emps = service.getAllEmployees();
		
		//getting the no of records 
		int totalCount = emps.size();
		
		//calculating the no of pages
		int pages = totalCount / pageSize;
		
		//make one extra page for remaining records
		if (pageNo == 0 || totalCount % pageSize > 0) {
			pages++;
		}
		
		//send the start position to UI
		map.addAttribute("startPosition", (pageNo * pageSize) - pageSize);
		
		//send the Page Size to UI
		map.addAttribute("pageSize", pageSize);
		//send the pages number of pages
		map.addAttribute("pages", pages);
		
		//send the data to UI
		map.addAttribute("emps", emps);
		return "EmployeeData";
	}

}
