package com.corporatemasters.workinsights.repository;

import com.corporatemasters.workinsights.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
