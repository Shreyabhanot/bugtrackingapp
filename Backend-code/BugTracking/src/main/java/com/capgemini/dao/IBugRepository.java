package com.capgemini.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Bug;


@Repository("bugRepository")
public interface IBugRepository extends JpaRepository<Bug, Integer>{

	@Query("Select b From Bug b Where b.status = :status")
	public List<Bug> getAllBugsByStatus(@Param("status")String status);
	
	@Query("Select b From Bug b Where b.priority = :priority")
	public List<Bug> getAllBugsByPriority(@Param("priority")String priority);

	@Query("Select b From Bug b Where b.project.projectId = :projectId")
	public List<Bug> getAllBugsByProjectId(@Param("projectId") int projectId);
	
	@Query("Select b From Bug b Where b.employee.employeeId = :employeeId")
	public List<Bug> getAllBugsByEmployeeId(@Param("employeeId") int empId);
	
	@Query("Select b From Bug b Where b.project.projectId= ?1 AND b.employee.employeeId= ?2")
	public List<Bug> getAllBugByProjectIdAndByEmployeeId(@Param("projectId") int projectId, @Param("employeeId") int employeeId);
	 

}
