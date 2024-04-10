package com.corporatemasters.workinsights.controller;

import com.corporatemasters.workinsights.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;
    @GetMapping("/statistics")
    public String listStatistics(){
        return "statistics";
    }
    @GetMapping("/statistics/commonProjects")
    public String getCommonProjectsStatistics(Model model) {
        Map.Entry<Set<Long>, HashMap<Long, Long>> best = statisticsService.getBestPair();
        long employeeId1 = best.getKey().stream().toList().get(0);
        long employeeId2 = best.getKey().stream().toList().get(1);
        HashMap<Long,Long> projectsDays = best.getValue();
        long totalDays = 0L;
        for (Map.Entry<Long,Long> entry : projectsDays.entrySet()) {
            totalDays+= entry.getValue();
        }
        model.addAttribute("employee1", employeeId1);
        model.addAttribute("employee2", employeeId2);
        model.addAttribute("total", totalDays);
        model.addAttribute("map", projectsDays);
        return "employeesProjects";
    }
}
