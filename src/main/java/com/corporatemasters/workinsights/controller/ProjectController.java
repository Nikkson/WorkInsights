package com.corporatemasters.workinsights.controller;

import com.corporatemasters.workinsights.model.Project;
import com.corporatemasters.workinsights.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @GetMapping("/projects")
    public String listProjects(Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "projects";
    }
    @GetMapping("/projects/add")
    public String addProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "projectEdit";
    }
    @PostMapping("/projects/add")
    public String addProject(@ModelAttribute Project project) {
        projectService.save(project);
        return "redirect:/projects";
    }
    @GetMapping("/projects/edit/{id}")
    public String editProjectForm(@PathVariable Long id, Model model) {
        model.addAttribute("project", projectService.findById(id));
        return "projectEdit";
    }
    @PostMapping("/projects/edit/{id}")
    public String editProject(@ModelAttribute Project project) {
        projectService.save(project);
        return "redirect:/projects";
    }
}
