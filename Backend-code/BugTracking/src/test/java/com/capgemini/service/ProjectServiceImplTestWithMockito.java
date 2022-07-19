package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.dao.IProjectRepository;
import com.capgemini.exception.NoSuchProjectException;
import com.capgemini.model.Project;

@SpringBootTest
class ProjectServiceImplTestWithMockito {

	@Autowired
	ProjectServiceImpl projectService;
	@MockBean
	private IProjectRepository projectRepository;
	

	
	@Test
	public void testaddProjectShoulReturnNewProject() {
		Project project = new Project("Sushma","open");
		Mockito.when(projectRepository.save(project)).thenReturn(project);
		Project result = projectService.addProject(project);
		projectService.addProject(project);
		assertEquals(project,result);
		
	}
	
	
	@Test
	void testupdateProjectShouldReturnUpdatedProject() {
		Project project = new Project ("Pratyusha","close");
		project.setProjectId(1);
		project.setProjectOwner("Pratyusha");
		Mockito.when(projectRepository.findById(1)).thenReturn(Optional.of(project));
		Mockito.when(projectRepository.save(project)).thenReturn(project);
		Project result = projectService.updateProject(project);
		assertEquals(project,result);
	}
	
	@Test
	void testDeleteProjectByIdShouldReturnTrueValue() throws NoSuchProjectException {
		Project project = new Project("Sushma","open");
		project.setProjectId(7);
		when(projectRepository.findById(7)).thenReturn(Optional.of(project));
		assertTrue(projectService.deleteProject(7));
	}
	
	@Test
	void testGetAllProjectsShouldReturnAllProjectlist() {
		List <Project> list = new ArrayList<>();
		Mockito.when(projectRepository.findAll()).thenReturn(list);
		List<Project> result = projectService.getAllProjects();
		assertEquals(list,result);
	}
	
	@Test
	void testGetProjectByIdShouldReturnProject () throws NoSuchProjectException {
		Project project = new Project ("Sushma","open");
		project.setProjectId(1);
		Optional<Project> expected = Optional.of(project);
		Mockito.when(projectRepository.findById(1)).thenReturn(expected);
	}

}
