package com.corporatemasters.workinsights.repository;

import com.corporatemasters.workinsights.model.EmployeeProject;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;


public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into employee_project (employee_id, project_id, start_date, end_date) values (:employeeId, :projectId, :startDate, :endDate)", nativeQuery = true)
    void save(@Param("employeeId")Long employeeId, @Param("projectId")Long projectId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
    @Query(value = "select case when count(id)> 0 then 'True' else 'False' end from employee_project ep where employee_id=(:employeeId) and project_id=(:projectId) and start_date < (:endDate) and end_date > (:startDate)", nativeQuery = true)
    boolean exists(@Param("employeeId")Long employeeId, @Param("projectId")Long projectId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
