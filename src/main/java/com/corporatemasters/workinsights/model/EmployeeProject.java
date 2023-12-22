package com.corporatemasters.workinsights.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class EmployeeProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee employee;
    @ManyToOne
    @JoinColumn(name = "project_id")
    Project project;
    Date startDate;
    Date endDate;
}
