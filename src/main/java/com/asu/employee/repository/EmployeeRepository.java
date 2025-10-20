package com.asu.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asu.employee.Dto.Employee;
import com.asu.employee.Dto.EmployeeResponse;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
	boolean existsByEmail(String email);
	
	@Query(value="select id,name,email,salary from employee where salary in (select max(salary) from employee)",nativeQuery=true)
	public List<EmployeeResponse> findMaxSalaryEmployee();
	
	
}
