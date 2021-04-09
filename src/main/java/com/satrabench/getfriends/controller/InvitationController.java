package com.satrabench.getfriends.controller;

import com.satrabench.getfriends.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InvitationController {

    private final InvitationService invitationService;

    @Autowired
    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @GetMapping("/invite")
    public ResponseEntity<Object> getAll(){
        return invitationService.getAll();
    }

    @PostMapping("/invite/create")
    public ResponseEntity<Object> create(@RequestParam int idSuper, @RequestParam int idUser){
        return invitationService.create(idSuper, idUser);
    }

    @GetMapping("/invite/accept")
    public ResponseEntity<Object> accept(@RequestParam(required=false) Integer idInvitation ) {
        return invitationService.accept(idInvitation);
    }

    @GetMapping("/invite/deny")
    public ResponseEntity<Object> deny(@RequestParam(required=false) Integer idInvitation ) {
        return invitationService.deny(idInvitation);
    }


}
