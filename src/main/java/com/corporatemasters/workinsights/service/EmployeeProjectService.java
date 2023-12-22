package com.corporatemasters.workinsights.service;

import com.corporatemasters.workinsights.dto.EmployeeProjectDTO;
import com.corporatemasters.workinsights.repository.EmployeeProjectRepository;
import com.corporatemasters.workinsights.repository.EmployeeRepository;
import com.corporatemasters.workinsights.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeProjectService {
    @Autowired
    EmployeeProjectRepository employeeProjectRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProjectRepository projectRepository;
    public void save(EmployeeProjectDTO employeeProjectDTO) {
        employeeProjectRepository.save(employeeProjectDTO.getEmployeeId(), employeeProjectDTO.getProjectId(), employeeProjectDTO.getStartDate(), employeeProjectDTO.getEndDate());
    }
    public void saveAll(List<EmployeeProjectDTO> employeeProjectDTOs) {
        for (EmployeeProjectDTO employeeProjectDTO : employeeProjectDTOs) {
            employeeProjectRepository.save(employeeProjectDTO.getEmployeeId(), employeeProjectDTO.getProjectId(), employeeProjectDTO.getStartDate(), employeeProjectDTO.getEndDate());
        }
    }
    public void saveAllIfNotExist(List<EmployeeProjectDTO> employeeProjectDTOs) {
        for (EmployeeProjectDTO employeeProjectDTO : employeeProjectDTOs) {
            if (exists(employeeProjectDTO)) {
                throw new IllegalArgumentException("One or more records are overlapping!");
            }
            if (!employeeRepository.existsById(employeeProjectDTO.getEmployeeId())) {
                throw new IllegalArgumentException("One or more employee IDs do not exist!");
            }
            if (!projectRepository.existsById(employeeProjectDTO.getProjectId())) {
                throw new IllegalArgumentException("One or more project IDs do not exist!");
            }
        }
        saveAll(employeeProjectDTOs);
    }
    public boolean exists(EmployeeProjectDTO employeeProjectDTO) {
        return employeeProjectRepository.exists(employeeProjectDTO.getEmployeeId(), employeeProjectDTO.getProjectId(), employeeProjectDTO.getStartDate(), employeeProjectDTO.getEndDate());
    }
}
