package com.asu.employee.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.asu.employee.Dto.Employee;
import com.asu.employee.Dto.EmployeeRequest;
import com.asu.employee.Dto.EmployeeResponse;
import com.asu.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	public static class ResourceNotFoundException extends RuntimeException{
		public ResourceNotFoundException(String msg) {
			super(msg);
		}
	}
	
	private final EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository=employeeRepository;
	}
	
	public EmployeeResponse addEmployee(EmployeeRequest req) {
		
		if(employeeRepository.existsByEmail(req.getEmail())) {
			throw new IllegalArgumentException("Email already exists...");
		}
		
		Employee e=new Employee();
		e.setName(req.getName());
		e.setEmail(req.getEmail());
		e.setSalary(req.getSalary());
		
		Employee saved=employeeRepository.save(e);
		return mapToResponse(saved);
	}
	
	public EmployeeResponse getEmployee(Long id) {
		Employee e=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found with this id : "+id));
		return mapToResponse(e);
	}
	
	public List<EmployeeResponse> getAllEmployees(){
		return employeeRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}
	
	public EmployeeResponse updateEmployee(Long id,EmployeeRequest req) {
		Employee e=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found with this id : "+ id));
		e.setName(req.getName());
		e.setEmail(req.getEmail());
		e.setSalary(req.getSalary());
		Employee updated=employeeRepository.save(e);
		return mapToResponse(updated);
	}
	
	public String deleteEmployee(Long id) {
		Employee e=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not available with this id : "+id));
		
		employeeRepository.deleteById(id);
		return "Employee deleted successfully";
	}
	
	public List<EmployeeResponse> getMaxSalaryEmployee() {
		return employeeRepository.findMaxSalaryEmployee();
	}

	private EmployeeResponse mapToResponse(Employee e) {
		return new EmployeeResponse(e.getId(),e.getName(),e.getEmail(),e.getSalary());
	}

}
