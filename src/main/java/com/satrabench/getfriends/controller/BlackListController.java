package com.satrabench.getfriends.controller;


import com.satrabench.getfriends.model.BlackSite;
import com.satrabench.getfriends.service.BlackListService;
import com.satrabench.getfriends.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlackListController {

    private final BlackListService blacklistService;

    @Autowired
    public BlackListController(BlackListService blacklistService) {
        this.blacklistService = blacklistService;
    }

    @GetMapping("/blacklist")
    public ResponseEntity<Object> getAll(){
        return blacklistService.getAll();
    }

    @PostMapping("/blacklist/create")
    public ResponseEntity<Object> create(@RequestParam Integer idSuper, @RequestParam String blacklist){
        return blacklistService.create(idSuper, blacklist);
    }

    @PostMapping("/blacklist/add")
    public ResponseEntity<Object> add(@RequestParam Integer idSuper, @RequestParam String blacklist){
        return blacklistService.add(idSuper, blacklist);
    }
}
