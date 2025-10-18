package com.asu.employee.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
	
	private Long id;
	private String name;
	private String email;
	private BigDecimal salary;

}
