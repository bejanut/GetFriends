package com.satrabench.getfriends.controller;

import com.satrabench.getfriends.model.Project;
import com.satrabench.getfriends.model.Supervised;
import com.satrabench.getfriends.model.Task;
import com.satrabench.getfriends.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/project")
    public ResponseEntity<Object> getAll(){
        return projectService.getAll();
    }

    @PostMapping("/project/create")
    public ResponseEntity<Object> create(@RequestBody Project project){
        return projectService.create(project);
    }

    @GetMapping("/project/completed")
    public ResponseEntity<Object> calculateCompletedTasks(@RequestParam Integer id) {return projectService.calculateCompletedTasks(id); }

    @GetMapping("/project/completion")
    public ResponseEntity<Object>  completion(@RequestParam Integer id) {return projectService.completion(id); }

    @GetMapping("/project/taskForProject")
    public ResponseEntity<Object> taskForProject(@RequestBody Supervised supervised,@RequestParam Integer projectId,@RequestBody Task task) {return projectService.taskForProject(supervised,projectId,task); }

}
