package com.corporatemasters.workinsights.service;

import com.corporatemasters.workinsights.model.Project;
import com.corporatemasters.workinsights.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
    public void save(Project project) {
        projectRepository.save(project);
    }
}
