package com.asu.employee.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.Optional;

import com.asu.employee.Dto.Employee;
import com.asu.employee.Dto.EmployeeRequest;
import com.asu.employee.Dto.EmployeeResponse;
import com.asu.employee.repository.EmployeeRepository;
import static org.junit.jupiter.api.Assertions.*;


public class EmployeeServiceTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
    @Test
    void testAddEmployee() {
    	Employee emp = new Employee(null,"Asutosh", "test@mail.com", new BigDecimal("50000"));
        when(employeeRepository.save(emp)).thenReturn(emp);

        EmployeeRequest request = new EmployeeRequest();
        request.setName("Asutosh");
        request.setEmail("test@mail.com");
        request.setSalary(new BigDecimal("50000"));

        EmployeeResponse result = employeeService.addEmployee(request);

        assertEquals("Asutosh", result.getName());
    }

//
//	@Test
//    void testFindEmployeeById() {
//        Employee emp = new Employee(null, "Asutosh", "test@mail.com", new BigDecimal("50000"));
//        when(employeeRepository.findById("1")).thenReturn(Optional.of(emp));
//
//        Employee result = employeeService.findEmployeeById("1");
//        assertNotNull(result);
//        assertEquals("1", result.getId());
//    }
//
//    @Test
//    void testGetAllEmployees() {
//        Employee emp1 = new Employee("1", "Asutosh", "test@mail.com", "50000");
//        Employee emp2 = new Employee("2", "Rohan", "rohan@mail.com", "60000");
//        when(employeeRepository.findAll()).thenReturn(Arrays.asList(emp1, emp2));
//
//        List<Employee> list = employeeService.getAllEmployees();
//        assertEquals(2, list.size());
//    }
//
//    @Test
//    void testDeleteEmployee() {
//        Employee emp = new Employee("1", "Asutosh", "test@mail.com", "50000");
//        doNothing().when(employeeRepository).deleteById("1");
//
//        assertDoesNotThrow(() -> employeeService.deleteEmployee("1"));
//        verify(employeeRepository, times(1)).deleteById("1");
//    }

}
