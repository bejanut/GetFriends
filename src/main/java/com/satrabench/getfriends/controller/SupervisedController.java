package com.satrabench.getfriends.controller;

import com.satrabench.getfriends.model.Project;
import com.satrabench.getfriends.model.Supervised;
import com.satrabench.getfriends.service.SupervisedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supervised")
public class SupervisedController {

    private final SupervisedService supervisedService;
    private final SupervisedService userService;

    @Autowired
    public SupervisedController(SupervisedService supervisedService, SupervisedService userService) {
        this.supervisedService = supervisedService;
        this.userService = userService;
    }

    @GetMapping("/getsupervised")
    public ResponseEntity<Object> getOpen(@RequestParam("id") int id){

        return supervisedService.getSupervised(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody Supervised supervised){
        return supervisedService.create(supervised);
    }

    @PostMapping("/getAllTasks")
    public ResponseEntity<Object> getAllTasks(@RequestParam("id") int id) {

        return supervisedService.getAllTasks(id);
    }

    @GetMapping("/addProject")
    public ResponseEntity<Object> projectToSupervised(@RequestParam Integer supervisedId, @RequestBody Project project ) {
        return supervisedService.projectToSupervised(supervisedId, project);
    }

    @PostMapping("/addBlacklist")
    public ResponseEntity<Object> addBlacklist(@RequestParam Integer id, @RequestParam String siteName) {
        return supervisedService.addBlacklist(id, siteName);
    }

    @GetMapping("/getAllInvites")
    public ResponseEntity<Object> getAllInvites(@RequestParam Integer supervisedId) {
        return supervisedService.getAllInvites(supervisedId);
    }

}
