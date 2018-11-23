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
	
	//3. display data
	@RequestMapping("/all")
	public String displayData(ModelMap map){
					
		int pageSize=5;
		
		List<Employee> emps=service.getAllEmployees(1,pageSize);
		System.out.println(emps.size());
		int totalCount=service.getTotalCount();
		int pages=totalCount/pageSize;
		System.out.println(totalCount+"  is Equal to "+emps.size());
		if (totalCount/emps.size()>0) {
			pages++;
			System.out.println("pages are "+pages);
		}
		map.addAttribute("pages", pages);
		map.addAttribute("emps", emps);
		return "EmployeeData";
	}
	@RequestMapping("/page/{id}")
	public String displayPageData(@PathVariable("id") Integer pageNo,ModelMap map){
		
		int pageSize=5;
	
		
		System.out.println("path variable is "+pageNo);
		int totalCount=service.getTotalCount();
		List<Employee> emps=service.getAllEmployees(pageNo,pageSize);
		System.out.println(totalCount+"  is Equal to "+emps.size());
		int pages=totalCount/pageSize;
		System.out.println("pages are "+totalCount/pageSize+" if "+(totalCount%pageSize>0));
		if (pageNo==0 || totalCount%pageSize>0) {
			pages++;
			System.out.println("pages are "+pages);
		}
		map.addAttribute("emps", emps);
		map.addAttribute("pages", pages);
		return "EmployeeData";
	}
}
