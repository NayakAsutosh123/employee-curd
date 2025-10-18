package com.asu.employee.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asu.employee.Dto.EmployeeRequest;
import com.asu.employee.Dto.EmployeeResponse;
import com.asu.employee.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	@PostMapping("/add")
	public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest req){
	EmployeeResponse created=employeeService.addEmployee(req);
	return ResponseEntity.created(URI.create("/employee/create/"+created.getId())).body(created);
}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Long id){
		EmployeeResponse resp=employeeService.getEmployee(id);
		return ResponseEntity.ok(resp);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<EmployeeResponse>> getAll(){
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeRequest req){
		EmployeeResponse updated=employeeService.updateEmployee(id, req);
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok("Emplpoyee Deleted successfully....");
	}

}
