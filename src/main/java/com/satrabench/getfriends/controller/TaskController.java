package com.satrabench.getfriends.controller;

import com.satrabench.getfriends.model.Task;
import com.satrabench.getfriends.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<Object> getAll() {
        return taskService.getAll();
    }

    @PostMapping("/tasks/create")
    public ResponseEntity<Object> create(@RequestBody Task task) {
        return taskService.create(task);
    }
}
