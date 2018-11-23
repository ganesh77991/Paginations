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

	
	@Override
	//@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees(Integer pageNo, int pageSize) {

		return (List<Employee>) ht.execute(new HibernateCallback<List<Employee>>() {

			@Override
			public List<Employee> doInHibernate(Session session) throws HibernateException {

				// session=this.session;
				Criteria query = session.createCriteria(Employee.class);
				System.out.println(
						"EmployeeDaoImpl.getAllEmployees(...).new HibernateCallback() {...}.doInHibernate() pane No: "
								+ pageNo);

				int start_position=(pageNo*pageSize)-pageSize;
				System.out.println("Actual Page No is  " + (pageNo - 1)+" edited Page is "+start_position);
				query.setFirstResult(start_position);
				query.setMaxResults(pageSize);
				List<Employee> list = query.list();

				System.out.println(list);

				return list;
			}

		});
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return ht.loadAll(Employee.class).size();
	}	

}
