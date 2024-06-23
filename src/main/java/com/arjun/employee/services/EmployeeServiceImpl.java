package com.arjun.employee.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arjun.employee.entity.EmployeeEntity;
import com.arjun.employee.model.Employee;
import com.arjun.employee.repositroy.EmployeeRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		
		EmployeeEntity employeeEntity = new EmployeeEntity();
		
		BeanUtils.copyProperties(employee, employeeEntity);
		
		employeeRepository.save(employeeEntity);
		
		return employee;
	}

	public List<Employee> getAllEmployess() {
		List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
	    
	    List<Employee> employees = employeeEntities.stream()
	            .map(employeeEntity -> {
	                Employee employee = new Employee();
	                employee.setId(employeeEntity.getId());
	                employee.setFirstName(employeeEntity.getFirstName());
	                employee.setLastName(employeeEntity.getLastName());
	                employee.setEmail(employeeEntity.getEmail());
	                return employee;
	            })
	            .collect(Collectors.toList());
	    
	    return employees;
	}

	@Override
	public boolean deleteEmployee(Long id) {
		
		EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
		employeeRepository.delete(employeeEntity);
		return true;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		EmployeeEntity entity = employeeRepository.findById(id).get();
		
		Employee employee = new Employee();
		
		BeanUtils.copyProperties(entity, employee);
		
		
		return employee;
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		
		EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
		
		if(employeeEntity != null) {
			employeeEntity.setEmail(employee.getEmail());
			employeeEntity.setFirstName(employee.getFirstName());
			employeeEntity.setLastName(employee.getLastName());
			employeeRepository.save(employeeEntity);
		}
		
		return employee;
	}
	
	
	
}
