package com.corporatemasters.workinsights.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String customer;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<EmployeeProject> employeeProjects;

    public Project() {
    }

    public Project(long id, String name, String description, String customer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.customer = customer;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Set<EmployeeProject> getEmployeeProjects() {
        return employeeProjects;
    }

    public void setEmployeeProjects(Set<EmployeeProject> employeeProjects) {
        this.employeeProjects = employeeProjects;
    }
}
