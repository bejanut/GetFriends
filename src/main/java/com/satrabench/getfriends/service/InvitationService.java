package com.satrabench.getfriends.service;

import com.satrabench.getfriends.model.Invitation;
import com.satrabench.getfriends.model.Supervised;
import com.satrabench.getfriends.model.User;
import com.satrabench.getfriends.repository.InvitationRepository;
import com.satrabench.getfriends.repository.SupervisedRepository;
import com.satrabench.getfriends.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;
    private final SupervisedRepository supervisedRepository;

    @Autowired
    public InvitationService(InvitationRepository invitationRepository, UserRepository userRepository, SupervisedRepository supervisedRepository) {
        this.invitationRepository = invitationRepository;
        this.userRepository = userRepository;
        this.supervisedRepository = supervisedRepository;
    }

    public ResponseEntity<Object> getAll(){
        List<Invitation> invitations = invitationRepository.findAll();
        return new ResponseEntity<Object>(invitations, HttpStatus.OK);
    }

    public ResponseEntity<Object> create(int idSuper, int idUser) {
        Invitation i = invitationRepository.save(new Invitation(idSuper, idUser));
        User u = userRepository.findById(idUser);
        i.setUser(u);
        invitationRepository.save(i);

        return new ResponseEntity<Object>(i, HttpStatus.OK);
    }

    public ResponseEntity<Object> accept(Integer idInvitation) {
        Invitation i = invitationRepository.findById(idInvitation).get();
        i.setStatus("accepted");

        User user = userRepository.findById(i.getIdUser()); //supervised to user
        Supervised supervised = supervisedRepository.findById(i.getIdSuper());

        Supervised supervised1 = supervisedRepository.save(supervised);
        user.getIncidents().add(supervised1);
        userRepository.save(user);

        return new ResponseEntity<Object>(i, HttpStatus.OK);
    }

    public ResponseEntity<Object> deny(Integer idInvitation) {
        Invitation i = invitationRepository.findById(idInvitation).get();
        i.setStatus("denied");

        User user = userRepository.findById(i.getIdUser());
        user.getInvitations().remove(i);
        userRepository.save(user);

        return new ResponseEntity<Object>(i, HttpStatus.OK);
    }
}

