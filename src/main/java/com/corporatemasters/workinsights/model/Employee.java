package com.corporatemasters.workinsights.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String department;
    private String position;
    private double salary;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<EmployeeProject> employeeProjects;

    public Employee() {
    }

    public Employee(long id, String name, String department, String position, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.position = position;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Set<EmployeeProject> getEmployeeProjects() {
        return employeeProjects;
    }

    public void setEmployeeProjects(Set<EmployeeProject> employeeProjects) {
        this.employeeProjects = employeeProjects;
    }
}
