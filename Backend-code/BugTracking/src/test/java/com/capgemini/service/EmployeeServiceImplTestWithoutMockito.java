package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.exception.EmployeeValidationException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.model.Employee;

@SpringBootTest
class EmployeeServiceImplTestWithoutMockito {

	@Autowired
	private IEmployeeService employeeService;

	@Test
	void testAddEmployeeShouldReturnEmployeeObject() throws EmployeeValidationException {
		Employee employee = new Employee("Kanika Mathur", "kani@23gmail.com", "7874130587");
		assertEquals(employee, employeeService.createEmployee(employee));
	}

	@Test
	void testFindAllEmployees() {
		assertEquals(1, employeeService.getAllEmployees().size());
	}

	@Test
	void testGetEmployeeByIdShouldReturnEmployeeObject() throws EmployeeValidationException, NoSuchEmployeeException {

		Employee employee = new Employee("Shreya bhanot", "shre34@gmail.com", "9884565767");
		employeeService.createEmployee(employee);
		Employee result = employeeService.getEmployee(employee.getemployeeId());
		assertEquals(employee.getEmpName(), result.getEmpName());
		assertEquals(employee.getEmail(), result.getEmail());
		assertEquals(employee.getContactNo(), result.getContactNo());

	}

	@Test
	void deleteEmployeeShouldReturnTrue() throws NoSuchEmployeeException {
		assertEquals(true, employeeService.deleteEmployee(3));
	}

	@Test
	void updateEmployeeShouldReturnUpdatedObject() throws NoSuchEmployeeException, EmployeeValidationException {
		Employee employee = new Employee("Sushma Kara", "sushma12@gmail.com", "9856431200");

		employeeService.createEmployee(employee);
		employee.setEmpName("Kara Sushma");
		employeeService.updateEmployee(employee);
		assertEquals(employee, employeeService.updateEmployee(employee));

	}

	@Test
	void testFindEmployeeByIdshouldThrowNoSuchEmployeeException() {
		assertThrows(NoSuchEmployeeException.class, () -> {
			employeeService.getEmployee(-1);
		});

	}
}
