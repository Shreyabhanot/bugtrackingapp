package com.capgemini.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.dao.IEmployeeRepository;
import com.capgemini.exception.EmployeeValidationException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.model.Employee;

@SpringBootTest
class EmployeeServiceImplTestWithMockito {

	@Autowired
	IEmployeeService employeeService;

	@MockBean
	private IEmployeeRepository employeeRepository;

	@Test
	void testAddEmployeeShouldReturnNewEmployee() throws EmployeeValidationException {
		Employee employee = new Employee("Kanikaa Mathurr", "Kanikaa20@gmail.com", "1234567898");
		Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
		Employee result = employeeService.createEmployee(employee);
		assertEquals(employee, result);
	}

	@Test
	void testupdateEmployeeShouldReturnUpdatedEmployee() throws NoSuchEmployeeException, EmployeeValidationException {
		Employee employee = new Employee("Kan Mahurr", "Kanika20@gmail.com", "1234967898");
		employee.setemployeeId(1);
		employee.setEmpName("Kanika Mathur");
		Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
		Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

		Employee result = employeeService.updateEmployee(employee);
		assertEquals(employee, result);
	}

	@Test
	void testGetAllEmployeesShouldReturnAllEmployeelist() {
		List<Employee> list = new ArrayList<>();
		Mockito.when(employeeRepository.findAll()).thenReturn(list);
		List<Employee> result = employeeService.getAllEmployees();
		assertEquals(list, result);
	}

	@Test
	void testGetEmployeeByIdShouldReturnEmployee() throws NoSuchEmployeeException, EmployeeValidationException {
		Employee employee = new Employee("Usha", "usha@23.com", "2534546354");
		employee.setemployeeId(1);
		Optional<Employee> expected = Optional.of(employee);
		Mockito.when(employeeRepository.findById(1)).thenReturn(expected);
	}

	@Test
	void testDeleteEmployeeByIdShouldReturnTrueValue() throws NoSuchEmployeeException {
		Employee employee = new Employee("Kan Mahurr", "Kanika20@gmail.com", "1234967898");
		employee.setemployeeId(5);
		Mockito.when(employeeRepository.findById(5)).thenReturn(Optional.of(employee));
		assertTrue(employeeService.deleteEmployee(5));
	}
	

}
