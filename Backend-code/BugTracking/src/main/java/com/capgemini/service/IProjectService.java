package com.capgemini.service;

import java.util.List;

import com.capgemini.exception.NoSuchProjectException;
import com.capgemini.model.Project;

public interface IProjectService {

	public Project addProject(Project project);

	public boolean deleteProject(int id) throws NoSuchProjectException;

	public Project updateProject(Project project);

	public List<Project> getAllProjects();

	public Project findProjectById(int projectId) throws NoSuchProjectException;

	/* public List<Project> getAllProjectsByStatus(String status); */

	public List<Project> getAllProjectsByStatusOrId(String status);
}
