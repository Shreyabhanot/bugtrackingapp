package com.capgemini.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Project;



@Repository("projectRepository")
public interface IProjectRepository extends JpaRepository<Project, Integer> {
	
	/*
	 * @Query("Select p from Project p where p.status = :status") public
	 * List<Project> getAllProjectsByStatus(@Param("status")String status);
	 */
	
	@Query("Select p from Project p where p.status like %:status% or p.id like %:status%")
	public List<Project> getAllProjectsByStatusOrId(@Param("status")String status);

}