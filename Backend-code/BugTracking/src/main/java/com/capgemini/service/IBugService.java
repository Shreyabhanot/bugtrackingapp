package com.capgemini.service;

import java.util.List;

import com.capgemini.exception.NoSuchBugFoundException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.exception.NoSuchProjectException;
import com.capgemini.exception.NotValidEndDateException;
import com.capgemini.model.Bug;

public interface IBugService {
	public Bug createBug(Bug bug) throws NoSuchProjectException, NoSuchEmployeeException, NotValidEndDateException;
	public Bug updateBug(Bug bug);
	public Bug getBugById(int id) throws NoSuchBugFoundException;
	public List<Bug> getAllBugs();
	public List<Bug> getAllBugsByStatus(String status);
	public boolean deleteBug(int id) throws NoSuchBugFoundException;
	public List<Bug> getAllBugsByProjectId(int projectId) throws NoSuchProjectException;
	public List<Bug> getAllBugsByEmployeeId(int employeeId) throws NoSuchEmployeeException;
	List<Bug> getAllBugByProjectIdAndByEmployeeId(int projectId, int employeeId) throws NoSuchProjectException, NoSuchEmployeeException;
	List<Bug> getAllBugsByPriority(String priority);

}
