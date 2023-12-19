package com.corporatemasters.workinsights.repository;

import com.corporatemasters.workinsights.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
