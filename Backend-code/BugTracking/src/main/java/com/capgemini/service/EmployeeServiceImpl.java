package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.constants.ExceptionHandlerMessages;
import com.capgemini.dao.IEmployeeRepository;
import com.capgemini.exception.EmployeeValidationException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.model.Employee;

@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Override
	@Transactional
	public Employee createEmployee(Employee employee) throws EmployeeValidationException {
		if (validateEmployee(employee) != null) {
			Employee result = employeeRepository.save(employee);
			return result;
		}
		throw new EmployeeValidationException(ExceptionHandlerMessages.NOT_VALID_EMPLOYEE);
	}

	private Employee validateEmployee(Employee employee) {
		if (!employee.getEmpName().matches("[a-zA-Z\\s]*$")) {
			return null;
		}

		if (!employee.getContactNo()
				.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")) {
			return null;
		}
		return employee;
	}

	@Override
	public Employee getEmployee(int id) throws NoSuchEmployeeException {
		Optional<Employee> result = employeeRepository.findById(id);
		Employee employee = null;
		if (result.isPresent()) {
			employee = result.get();
		} else {
			throw new NoSuchEmployeeException(ExceptionHandlerMessages.EMPLOYEE_ID_NOT_FOUND);
		}
		return employee;
	}

	@Transactional
	@Override
	public Employee updateEmployee(Employee employee) throws NoSuchEmployeeException {

		Optional<Employee> result=	employeeRepository.findById(employee.getemployeeId());
		if(result.isPresent()) {
			return employeeRepository.save(employee);
		}
		throw new NoSuchEmployeeException(ExceptionHandlerMessages.EMPLOYEE_ID_NOT_FOUND);
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	@Transactional
	public boolean deleteEmployee(int Id) throws NoSuchEmployeeException {

		Optional<Employee> result = employeeRepository.findById(Id);
		if (result.isPresent()) {
			employeeRepository.delete(result.get());
			return true;
		}
		throw new NoSuchEmployeeException(ExceptionHandlerMessages.EMPLOYEE_ID_NOT_FOUND);
	}

	@Override
	public Employee getAllEmployeeByEmail(String email) {
		return employeeRepository.getAllEmployeeByEmail(email);
	}

	/*
	 * @Override public List<Employee> getAllEmployeeByName(String empName) { return
	 * employeeRepository.getAllEmployeeByName(empName); }
	 */
	
	@Override
	public List<Employee>getAllEmployeesByNameOrId(String empName){
		List<Employee> result= employeeRepository.getAllEmployeesByNameOrId(empName);
		return result;
	}
}
