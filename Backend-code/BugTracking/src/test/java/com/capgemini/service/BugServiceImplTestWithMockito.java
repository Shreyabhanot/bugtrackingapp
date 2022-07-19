package com.capgemini.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.dao.IBugRepository;
import com.capgemini.exception.NoSuchBugFoundException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.exception.NoSuchProjectException;
import com.capgemini.exception.NotValidEndDateException;
import com.capgemini.model.Bug;
import com.capgemini.model.Employee;
import com.capgemini.model.Project;

@SpringBootTest
class BugServiceImplTestWithMockito {

	@Autowired
	private BugServiceImpl bugService;

	@MockBean
	private IBugRepository bugRepository;

	@Test
	void testCreateBugShouldReturnBug()
			throws NoSuchProjectException, NoSuchEmployeeException, NotValidEndDateException {
		String strt_dt = "2021-02-15";
		Bug bug = new Bug("Bug found while logging in user", "Open", Date.valueOf(strt_dt), null, "Sneha", "Functional Bug",
				"High");
		bug.setBugId(2);
		Project project = new Project();
		project.setProjectId(1);
		bug.setProject(project);
		Employee employee = new Employee();
		employee.setemployeeId(5);
		bug.setEmployee(employee);

		when(bugRepository.save(bug)).thenReturn(bug);
		Bug result = bugService.createBug(bug);
		assertEquals(bug, result);
	}

	@Test
	void testFindBugByIdShouldReturnObject() throws NoSuchBugFoundException {
		String strt_dt = "2021-02-15";
		Bug bug = new Bug("Bug found while creating user", "Open", Date.valueOf(strt_dt), null, "Sneha", "Functional Bug",
				"High");
		bug.setBugId(1);
		Optional<Bug> expected = Optional.of(bug);
		when(bugRepository.findById(1)).thenReturn(expected);

		Bug result = bugService.getBugById(bug.getBugId());
		assertEquals(bug, result);
	}

	@Test
	void testFindAllBugsShouldReturnListOfBugs() {

		String strt_dt = "2021-02-01";
		Bug bug = new Bug("Bug found while entering mail Id", "Open", Date.valueOf(strt_dt), null, "Rahul", "Validation Bug",
				"High");
		bug.setBugId(2);
		Project project = new Project();
		project.setProjectId(1);
		bug.setProject(project);
		Employee employee = new Employee();
		employee.setemployeeId(5);
		bug.setEmployee(employee);
		List<Bug> list = new ArrayList<>();
		list.add(bug);
		when(bugRepository.findAll()).thenReturn(list);
		List<Bug> result = bugService.getAllBugs();
		assertEquals(list, result);

	}

	@Test
	void testUpdateBugShouldReturnUpdatedBug() {
		String strt_dt = "2021-02-01";
		Bug bug = new Bug("Bug found while entering mail Id", "Open", Date.valueOf(strt_dt), null, "Rahul", "Validation Bug",
				"High");
		bug.setBugId(1);
		bug.setPriority("Medium");
		when(bugRepository.findById(1)).thenReturn(Optional.of(bug));
		when(bugRepository.save(bug)).thenReturn(bug);

		Bug result = bugService.updateBug(bug);
		assertEquals(bug, result);

	}

	@Test
	void testDeleteBugByIdShouldReturnTrue() throws NoSuchBugFoundException {
		String strt_dt = "2021-02-01";
		Bug bug = new Bug("Bug found while logging in user", "Open", Date.valueOf(strt_dt), null, "Sneha", "Functional Bug",
				"High");
		bug.setBugId(7);
		when(bugRepository.findById(7)).thenReturn(Optional.of(bug));
		assertTrue(bugService.deleteBug(7));
	}
	
	@Test
	void testGetAllBugsByRrojectId() throws NoSuchProjectException {
		String strt_dt = "2021-02-01";
		Bug bug = new Bug("Bug found while logging in user", "Open", Date.valueOf(strt_dt), null, "Sneha", "Functional Bug",
				"High");
		bug.setBugId(2);
		Project project = new Project();
		project.setProjectId(1);
		bug.setProject(project);
		Employee employee = new Employee();
		employee.setemployeeId(5);
		bug.setEmployee(employee);
		List<Bug> list = new ArrayList<>();
		list.add(bug);
		
		when(bugRepository.getAllBugsByProjectId(1)).thenReturn(list);
		List<Bug> result = bugService.getAllBugsByProjectId(1);
		assertEquals(list, result);
	}
	
	@Test
	void testGetAllBugByEmployeeId() throws NoSuchEmployeeException {
		String strt_dt = "2021-02-01";
		Bug bug = new Bug("Bug found while creating user", "Open", Date.valueOf(strt_dt), null, "Sneha", "Functional Bug",
				"High");
		bug.setBugId(2);
		Project project = new Project();
		project.setProjectId(1);
		bug.setProject(project);
		Employee employee = new Employee();
		employee.setemployeeId(5);
		bug.setEmployee(employee);
		List<Bug> list = new ArrayList<>();
		list.add(bug);
		
		when(bugRepository.getAllBugsByEmployeeId(5)).thenReturn(list);
		List<Bug> result = bugService.getAllBugsByEmployeeId(5);
		assertEquals(list, result);		
	}
	
	@Test
	void testGetAllBugsByProjectIdAndEmployeeId() throws NoSuchProjectException, NoSuchEmployeeException {
		String strt_dt = "2021-02-01";
		Bug bug = new Bug("Bug found while creating user", "Open", Date.valueOf(strt_dt), null, "Sneha", "Functional Bug",
				"High");
		bug.setBugId(2);
		Project project = new Project();
		project.setProjectId(1);
		bug.setProject(project);
		Employee employee = new Employee();
		employee.setemployeeId(5);
		bug.setEmployee(employee);
		List<Bug> list = new ArrayList<>();
		list.add(bug);
		
		when(bugRepository.getAllBugByProjectIdAndByEmployeeId(1,5)).thenReturn(list);
		List<Bug> result= bugService.getAllBugByProjectIdAndByEmployeeId(1, 5);
		assertEquals(list, result);
	}
}
