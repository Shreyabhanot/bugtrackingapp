package com.capgemini.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.constants.ExceptionHandlerMessages;
import com.capgemini.exception.NoSuchProjectException;
import com.capgemini.model.Project;
import com.capgemini.service.IProjectService;

@CrossOrigin
@RestController
@RequestMapping(path = "project")
public class ProjectRestController {

	@Autowired
	private IProjectService service;

	/**
	 * This will add the Project in Database
	 * 
	 * @param project
	 * @return project
	 */
	// http://localhost:9090/BugApp/project
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Project> addProject(@Valid @RequestBody Project project) {
		Project project1 = service.addProject(project);
		ResponseEntity<Project> response = null;
		response = new ResponseEntity<Project>(project1, HttpStatus.OK);
		return response;

	}

	/**
	 * This will find by Id of the Project in Database
	 * 
	 * @param projectId
	 * @return project
	 * @throws NoSuchProjectException
	 */
	// http://localhost:9090/BugApp/project/
	@GetMapping(path = "{projectId}", produces = "application/json")
	public ResponseEntity<Project> getProjectById(@Valid @PathVariable("projectId") int projectId)
			throws NoSuchProjectException {
		Project result = service.findProjectById(projectId);
		ResponseEntity<Project> response = null;
		if (result != null) {
			response = new ResponseEntity<Project>(result, HttpStatus.OK);
			return response;
		}
		throw new NoSuchProjectException(ExceptionHandlerMessages.PROJECT_ID_NOT_FOUND);
	}

	/**
	 * This will update the Project in Database
	 * 
	 * @param project
	 * @return result
	 * @throws NoSuchProjectException
	 */
	// http://localhost:9090/bug-api/project
	@PutMapping(consumes = "application/json")
	public Project updateProject(@Valid @RequestBody Project project) throws NoSuchProjectException {
		Project result = service.updateProject(project);
		if (result != null) {
			return result;
		}
		throw new NoSuchProjectException("No Such Project exists");
	}

	/**
	 * This will delete Id of the Project in Database
	 * 
	 * @param id
	 * @return response
	 * @throws NoSuchProjectException
	 */
	// http://localhost:9090/BugApp/project
	@DeleteMapping(path = "{projectId}")
	public ResponseEntity<Project> deleteProject(@Valid @PathVariable("projectId") int id)
			throws NoSuchProjectException {
		boolean result = service.deleteProject(id);
		ResponseEntity<Project> response = null;
		if (result) {
			response = new ResponseEntity<Project>(HttpStatus.NO_CONTENT);
			return response;
		}
		throw new NoSuchProjectException(ExceptionHandlerMessages.PROJECT_ID_NOT_FOUND);
	}

	/**
	 * This will get all the Projects in Database
	 * 
	 * @return result
	 */
	// http://localhost:9090/BugApp/projects
	@GetMapping(produces = "application/json")
	public List<Project> getAllProjects() {
		List<Project> result = service.getAllProjects();
		return result;
	}

	/**
	 * This will get the Project Status and Id in Database
	 * 
	 * @param status
	 * @return status
	 */
	// http://localhost:9090/BugApp/project/byId/
	@GetMapping(path = "bystatusOrId/{status}", produces = "application/json")
	public List<Project> getAllProjectsByStatusOrId(@Valid @PathVariable("status") String status) {
		List<Project> result = service.getAllProjectsByStatusOrId(status);
		return result;
	}

	/**
	 * This will get the Project Status in Database
	 * 
	 * @param status
	 * @return status
	 */
	/*
	 * // http://localhost:9090/BugApp/project/byStatus/
	 * 
	 * @GetMapping(path = "byStatus/{status}", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public List<Project>
	 * getAllProjectsByStatus(@Valid @PathVariable("status") String status) {
	 * List<Project> result = service.getAllProjectsByStatus(status); return result;
	 * }
	 */

}
