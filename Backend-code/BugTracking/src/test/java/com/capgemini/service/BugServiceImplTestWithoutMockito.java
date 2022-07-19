package com.capgemini.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.exception.EmployeeValidationException;
import com.capgemini.exception.NoSuchBugFoundException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.exception.NoSuchProjectException;
import com.capgemini.exception.NotValidEndDateException;
import com.capgemini.model.Bug;
import com.capgemini.model.Employee;
import com.capgemini.model.Project;

@SpringBootTest
class BugServiceImplTestWithoutMockito {

	@Autowired
	private IBugService bugService;

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IEmployeeService employeeService;

	@Test
	void testAddBugShouldReturnBug() throws NoSuchProjectException, NoSuchEmployeeException, NotValidEndDateException,
			EmployeeValidationException {
		String strt_dt = "2021-02-14";

		Project project = new Project("Varsha", "Active");
		Employee emp = new Employee("Ankita Sharma", "ankita12@gmail.com", "9414324542");

		projectService.addProject(project);
		employeeService.createEmployee(emp);
		Bug bug = new Bug("Missing Condition in the calculation", "Open", Date.valueOf(strt_dt), null, "Rahul",
				"Missing Code Bug", "High");
		bug.setProject(project);
		bug.setEmployee(emp);
		assertEquals(bug, bugService.createBug(bug));
	}

	@Test
	void testGetBugByIdShouldReturnBug()
			throws NoSuchBugFoundException, NoSuchProjectException, NoSuchEmployeeException, NotValidEndDateException {
		String strt_dt = "2021-02-01";
		Bug bug = new Bug("Miscalculation in Arithmetic Operation in Billing", "Open", Date.valueOf(strt_dt), null,
				"Rahul", "Arithmetic Operation Bug", "High");
		bug.setProject(projectService.findProjectById(2));
		bug.setEmployee(employeeService.getEmployee(2));
		bugService.createBug(bug);
		Bug result = bugService.getBugById(bug.getBugId());
		assertEquals(bug.getBugDesc(), result.getBugDesc());
		assertEquals(bug.getStatus(), result.getStatus());
		assertEquals(bug.getStartDate(), result.getStartDate());
		assertEquals(bug.getEndDate(), result.getEndDate());
		assertEquals(bug.getAssignee(), result.getAssignee());
		assertEquals(bug.getType(), result.getType());
		assertEquals(bug.getPriority(), result.getPriority());
		assertEquals(bug.getProject().getProjectId(), result.getProject().getProjectId());
		assertEquals(bug.getEmployee().getemployeeId(), result.getEmployee().getemployeeId());
	}

	@Test
	void testDeleteBugById() throws NoSuchBugFoundException {
		assertTrue(bugService.deleteBug(2));
	}
	
	
	
	@Test
	void testDeleteBugByIdThrowsNoSuchBugFoundException() {
		assertThrows(NoSuchBugFoundException.class, () -> {
			bugService.getBugById(-1);
		});
	}

	@Test
	void testGetAllBugs() {
		assertEquals(1, bugService.getAllBugs().size());
	}

	@Test
	void testGetAllBugByProjectId() throws NoSuchProjectException {
		assertEquals(1, bugService.getAllBugsByProjectId(1).size());
	}

	@Test
	void testGetAllBugsByEmployeeId() throws NoSuchEmployeeException {
		assertEquals(1, bugService.getAllBugsByEmployeeId(1).size());
	}

	@Test
	void testGetAllBugsByProjectIdAndByEmployeeId() throws NoSuchProjectException, NoSuchEmployeeException {
		assertEquals(1, bugService.getAllBugByProjectIdAndByEmployeeId(1, 1).size());
	}

	@Test
	void testGetAllBugsByProjectIdThrowException() {
		assertThrows(NoSuchProjectException.class, () -> {
			bugService.getAllBugsByProjectId(-1);
		});
	}

	@Test
	void testGetAllBugsByEmployeeIdThrowNoEmployeeIdFoundException() {
		assertThrows(NoSuchEmployeeException.class, () -> {
			bugService.getAllBugsByEmployeeId(-1);
		});
	}

}
