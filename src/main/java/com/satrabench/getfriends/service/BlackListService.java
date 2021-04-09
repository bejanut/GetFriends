package com.satrabench.getfriends.service;

import com.satrabench.getfriends.model.BlackSite;
import com.satrabench.getfriends.model.Invitation;
import com.satrabench.getfriends.model.Supervised;
import com.satrabench.getfriends.model.User;
import com.satrabench.getfriends.repository.BlackSiteRepository;
import com.satrabench.getfriends.repository.SupervisedRepository;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlackListService {

    private final BlackSiteRepository blackSiteRepository;
    private final SupervisedRepository supervisedRepository;

    public BlackListService(BlackSiteRepository blackSiteRepository, SupervisedRepository supervisedRepository) {
        this.blackSiteRepository = blackSiteRepository;
        this.supervisedRepository = supervisedRepository;
    }

    public ResponseEntity<Object> getAll(){
        List<BlackSite> blacklist = blackSiteRepository.findAll();
        return new ResponseEntity<Object>(blacklist, HttpStatus.OK);
    }

    public ResponseEntity<Object> create(int idSuper, String site) {
        Supervised user = supervisedRepository.findById(idSuper);
        BlackSite site1 = blackSiteRepository.save(new BlackSite(site));

        site1.setUser(user);
        blackSiteRepository.save(site1);

        return new ResponseEntity<Object>(site1, HttpStatus.OK);
    }

    public ResponseEntity<Object> add(Integer idSuper, String blacklist) {
        Supervised user = supervisedRepository.findById(idSuper).get();
        BlackSite site = blackSiteRepository.save(new BlackSite(blacklist));

        site.setUser(user);
        blackSiteRepository.save(site);

        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }
}
