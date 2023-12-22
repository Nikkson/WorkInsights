package com.corporatemasters.workinsights.service;

import com.corporatemasters.workinsights.dto.EmployeeProjectDTO;
import com.corporatemasters.workinsights.repository.EmployeeProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeProjectService {
    @Autowired
    EmployeeProjectRepository employeeProjectRepository;
    public void save(EmployeeProjectDTO employeeProjectDTO) {
        employeeProjectRepository.save(employeeProjectDTO.getEmployeeId(), employeeProjectDTO.getProjectId(), employeeProjectDTO.getStartDate(), employeeProjectDTO.getEndDate());
    }
    public void saveAll(List<EmployeeProjectDTO> employeeProjectDTOs) {
        for (EmployeeProjectDTO employeeProjectDTO : employeeProjectDTOs) {
            employeeProjectRepository.save(employeeProjectDTO.getEmployeeId(), employeeProjectDTO.getProjectId(), employeeProjectDTO.getStartDate(), employeeProjectDTO.getEndDate());
        }
    }
}
