package com.corporatemasters.workinsights.controller;

import com.corporatemasters.workinsights.dto.EmployeeProjectDTO;
import com.corporatemasters.workinsights.service.EmployeeProjectService;
import com.corporatemasters.workinsights.util.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class FileUploadController {
    @Autowired
    EmployeeProjectService employeeProjectService;
    @GetMapping("statistics/commonProjects/upload")
    public String uploadEmployeesProjectsFileForm () {
        return "uploadEmployeesProjects";
    }
    @PostMapping("/statistics/commonProjects/upload")
    public String handleEmployeesProjectsFileUpload(@RequestParam("file")MultipartFile file) {
        try {
            List<EmployeeProjectDTO> employeeProjectDTOs = CSVReader.getAllEmployeeProjectRecords(file);
            employeeProjectService.saveAll(employeeProjectDTOs);
            return "uploadEmployeesProjects";
        } catch (IOException e) {
            return "uploadEmployeesProjects";
        }

    }
}
