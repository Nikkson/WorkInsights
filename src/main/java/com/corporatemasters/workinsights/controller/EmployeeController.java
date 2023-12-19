package com.corporatemasters.workinsights.controller;

import com.corporatemasters.workinsights.model.Employee;
import com.corporatemasters.workinsights.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/employees")
    public String listEmployees(Model model){
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employees";
    }
    @GetMapping("/employees/add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employeeEdit";
    }
    @PostMapping("/employees/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees";
    }
}
