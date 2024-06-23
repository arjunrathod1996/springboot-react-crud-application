package com.arjun.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.employee.entity.EmployeeEntity;
import com.arjun.employee.model.Employee;
import com.arjun.employee.repositroy.EmployeeRepository;
import com.arjun.employee.services.EmployeeService;
import com.arjun.employee.services.EmployeeServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl employeeServiceImpl;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/employess")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeServiceImpl.createEmployee(employee);
	}

// Using Constructor
//	@GetMapping("/employess")
//	public List<Employee> getAllEmployee(){
//		
//		List<EmployeeEntity> employee = employeeRepository.findAll();
//		
//		List<Employee> emp = employee.stream()
//				.map(e -> new Employee(
//						e.getId(),
//						e.getFirstName(),
//						e.getLastName(),
//						e.getEmail()))
//				.collect(Collectors.toList());
//		
//		return emp;
//	}
	
	
	// Using Without Constructor
//	@GetMapping("/employess")
//	public List<Employee> getAllEmployee(){
//	    
//	    List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
//	    
//	    List<Employee> employees = employeeEntities.stream()
//	            .map(employeeEntity -> {
//	                Employee employee = new Employee();
//	                employee.setId(employeeEntity.getId());
//	                employee.setFirstName(employeeEntity.getFirstName());
//	                employee.setLastName(employeeEntity.getLastName());
//	                employee.setEmail(employeeEntity.getEmail());
//	                return employee;
//	            })
//	            .collect(Collectors.toList());
//	    
//	    return employees;
//	}
	
	@GetMapping("/employess")
	public List<Employee> getAllEmployee(){
		
		
	    
	    return employeeServiceImpl.getAllEmployess();
	}
	
	
	@DeleteMapping("/employess/{id}")
	public ResponseEntity<Map<String,Boolean>> deletEmployee(@PathVariable Long id) {
		boolean deleted = false;
		
		deleted = employeeService.deleteEmployee(id);
		
		Map<String, Boolean> response = new HashMap<>();
		
		response.put("deleted",deleted);
		
		return ResponseEntity.ok(response);
		
	}
	
	
	@GetMapping("/employess/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		
		Employee employee = null;
		
		employee = employeeService.getEmployeeById(id);
		
		return ResponseEntity.ok(employee);
		
		
	}
	
	@PutMapping("/employess/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
			@RequestBody Employee employee){
		
		employee = employeeService.updateEmployee(id,employee);
		
		return ResponseEntity.ok(employee);
	}
	
	


}
