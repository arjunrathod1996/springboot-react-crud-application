package com.arjun.employee.services;

import com.arjun.employee.model.Employee;

public interface EmployeeService {

	Employee createEmployee(Employee employee);

	boolean deleteEmployee(Long id);

	Employee getEmployeeById(Long id);

	Employee updateEmployee(Long id, Employee employee);
	
	

}
