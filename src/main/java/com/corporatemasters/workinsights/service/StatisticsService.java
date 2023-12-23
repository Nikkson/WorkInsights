package com.corporatemasters.workinsights.service;

import com.corporatemasters.workinsights.model.EmployeeProject;
import com.corporatemasters.workinsights.repository.EmployeeProjectRepository;
import com.corporatemasters.workinsights.repository.EmployeeRepository;
import com.corporatemasters.workinsights.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class StatisticsService {
    @Autowired
    EmployeeProjectRepository employeeProjectRepository;
    private HashMap<Set<Long>,HashMap<Long,Long>> getStatistics() {
        HashMap<Set<Long>,HashMap<Long,Long>> statistics = new HashMap<>();
        List<EmployeeProject> employeeProjects = employeeProjectRepository.findAll();
        for (EmployeeProject employeeProject : employeeProjects) {
            Long currentEmployeeId = employeeProject.getEmployee().getId();
            List<EmployeeProject> overlappingEmployeeProjects = findAllOverlapping(employeeProject);
            for (EmployeeProject overlappingEmployeeProject : overlappingEmployeeProjects) {
                Long checkedEmployeeId = overlappingEmployeeProject.getEmployee().getId();
                Long checkedProjectId = overlappingEmployeeProject.getProject().getId();
                Date startDate;
                Date endDate;
                if(employeeProject.getStartDate().compareTo(overlappingEmployeeProject.getStartDate()) > 0) {
                    startDate = employeeProject.getStartDate();
                } else {
                    startDate = overlappingEmployeeProject.getStartDate();
                }
                if(employeeProject.getEndDate().compareTo(overlappingEmployeeProject.getEndDate()) > 0) {
                    endDate = overlappingEmployeeProject.getEndDate();
                } else {
                    endDate = employeeProject.getEndDate();
                }
                Long diff = endDate.getTime() - startDate.getTime();
                Long days = TimeUnit.MILLISECONDS.toDays(diff);
                Set<Long> employeePair = new HashSet<>(2);
                employeePair.add(currentEmployeeId);
                employeePair.add(checkedEmployeeId);
                if (statistics.containsKey(employeePair)) {
                    if (statistics.get(employeePair).containsKey(checkedProjectId)) {
                        //same pair, same project, new period
                        statistics.get(employeePair).put(checkedProjectId, statistics.get(employeePair).get(checkedProjectId)+days);
                    } else {
                        //same pair, new project
                        statistics.get(employeePair).put(checkedProjectId, days);
                    }
                } else {
                    //new pair
                    statistics.put(employeePair, new HashMap<>());
                    statistics.get(employeePair).put(checkedProjectId,days);
                }
            }
        }
        return statistics;
    }
    public Map.Entry<Set<Long>,HashMap<Long,Long>> getBestPair() {
        HashMap<Set<Long>,HashMap<Long,Long>> statistics = getStatistics();
        Map.Entry<Set<Long>,HashMap<Long,Long>> bestEntry = null;
        Long total = 0L;
        for (Map.Entry<Set<Long>,HashMap<Long,Long>> employeePair : statistics.entrySet()) {
            Long currentTotal = 0L;
            for (Map.Entry<Long,Long> daysOnProject : employeePair.getValue().entrySet()) {
                currentTotal+=daysOnProject.getValue();
            }
            if (currentTotal>total) {
                total = currentTotal;
                bestEntry = employeePair;
            }
        }
        return fixBestEntry(bestEntry);
    }
    private Map.Entry<Set<Long>,HashMap<Long,Long>> fixBestEntry(Map.Entry<Set<Long>,HashMap<Long,Long>> bestPair) {
        if (bestPair == null) {
            return null;
        }
        for (Map.Entry<Long,Long> entry : bestPair.getValue().entrySet()) {
            entry.setValue(entry.getValue()/2);
        }
        return bestPair;
    }
    private List<EmployeeProject> findAllOverlapping(EmployeeProject employeeProject) {
        return employeeProjectRepository.findAllOverlapping(employeeProject.getEmployee().getId(), employeeProject.getProject().getId(), employeeProject.getStartDate(), employeeProject.getEndDate());
    }
}
