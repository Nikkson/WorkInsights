package com.corporatemasters.workinsights.dto;

import java.time.LocalDate;
import java.util.Date;

public class EmployeeProjectDTO {
    private long employeeId;
    private long projectId;
    private Date startDate;
    private Date endDate;

    public EmployeeProjectDTO() {
    }

    public EmployeeProjectDTO(long employeeId, long projectId, Date startDate, Date endDate) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
