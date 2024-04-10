package com.corporatemasters.workinsights.service;

import com.corporatemasters.workinsights.model.Employee;
import com.corporatemasters.workinsights.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
    public Employee findById(long id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
    }
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }
    public void delete(long id) {
        employeeRepository.deleteById(id);
    }
}
