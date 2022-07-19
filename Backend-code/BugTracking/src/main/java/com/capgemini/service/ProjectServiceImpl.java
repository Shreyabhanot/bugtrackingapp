package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.constants.ExceptionHandlerMessages;
import com.capgemini.dao.IProjectRepository;
import com.capgemini.exception.NoSuchProjectException;
import com.capgemini.model.Project;

@Service("projectService")
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private IProjectRepository projectRepository;

	@Transactional
	@Override
	public Project addProject(Project project) {
		if (project == null) {
			return null;
		}
		Project result = projectRepository.save(project);
		return result;
	}

	@Transactional
	@Override
	public boolean deleteProject(int id) throws NoSuchProjectException {
		Optional<Project> project = projectRepository.findById(id);
		if (project.isPresent()) {
			projectRepository.deleteById(id);
			return true;
		}
		throw new NoSuchProjectException(ExceptionHandlerMessages.PROJECT_ID_NOT_FOUND);

	}

	@Transactional
	@Override
	public Project updateProject(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	@Override
	public Project findProjectById(int projectId) throws NoSuchProjectException {
		Optional<Project> result = projectRepository.findById(projectId);
		Project project = null;
		if (result.isPresent()) {
			project = result.get();
		} else {
			throw new NoSuchProjectException(ExceptionHandlerMessages.PROJECT_ID_NOT_FOUND);
		}
		return project;
	}
	@Override
	public List<Project>getAllProjectsByStatusOrId(String status){
		List<Project> result= projectRepository.getAllProjectsByStatusOrId(status);
		return result;
	}
}
