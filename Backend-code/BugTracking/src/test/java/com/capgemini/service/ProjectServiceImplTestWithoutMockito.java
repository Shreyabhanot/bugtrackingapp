package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.exception.NoSuchProjectException;
import com.capgemini.model.Project;

@SpringBootTest
class ProjectServiceImplTestWithoutMockito {

	@Autowired
	private IProjectService projectService;

	@Test
	void testAddProjectShouldReturnProjectObject(){
		Project project = new Project("Sushma", "Active");
		assertEquals(project, projectService.addProject(project));
	}

	@Test
	void testUpdateProjectShouldReturnProjectObject(){
		Project project = new Project("Reshma", "Active");
		assertEquals(project, projectService.updateProject(project));
	}

	@Test
	void testGetProjectByIdShouldReturnProjectObject() throws NoSuchProjectException{
		Project project = new Project("Pratyusha", "Closed");
		projectService.addProject(project);
		Project result = projectService.findProjectById(project.getProjectId());
		assertEquals(project.getProjectOwner(), result.getProjectOwner());
		assertEquals(project.getStatus(), result.getStatus());

	}

	@Test
	void testDeleteProjectByIdReturnTrue() throws NoSuchProjectException {
		assertEquals(true, projectService.deleteProject(3));
	}
	
	@Test
	void testFindProjectByIdshouldThrowNoSuchProjectExeption() {
		assertThrows(NoSuchProjectException.class,() -> {
			projectService.findProjectById(-1);
		});
	}
}
