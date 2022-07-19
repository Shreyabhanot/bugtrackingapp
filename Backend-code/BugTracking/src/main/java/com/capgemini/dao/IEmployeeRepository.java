package com.capgemini.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Employee;



@Repository("employeeRepository")
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("Select e From Employee e Where e.email = :email")
	public Employee getAllEmployeeByEmail(@Param("email") String email);
	
//	@Query("Select e From Employee e Where e.empName = :empName")
//	public List<Employee> getAllEmployeeByName(@Param("empName") String empName);
	
	@Query("Select e from Employee e where e.empName like %:empName% or e.id like %:empName%")
	public List<Employee> getAllEmployeesByNameOrId(@Param("empName")String empName);
	
}
