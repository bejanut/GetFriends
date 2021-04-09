package com.satrabench.getfriends.service;

import com.satrabench.getfriends.model.Project;
import com.satrabench.getfriends.model.Supervised;
import com.satrabench.getfriends.model.User;
import com.satrabench.getfriends.repository.ProjectRepository;

import com.satrabench.getfriends.model.*;
import com.satrabench.getfriends.repository.SupervisedRepository;
import com.satrabench.getfriends.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SupervisedService {

    private final UserRepository userRepository;

    private final SupervisedRepository supervisedRepository;
    private final ProjectRepository projectRepository;
    @Autowired
    public SupervisedService(UserRepository userRepository,
                             SupervisedRepository supervisedRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.supervisedRepository = supervisedRepository;
        this.projectRepository = projectRepository;
    }

    public ResponseEntity<Object> createSupervised(Supervised supervised, int userId) {
        User u = userRepository.findById(userId);
        supervised.setUser(u);
        Supervised supervised1 = supervisedRepository.save(supervised);
        u.getIncidents().add(supervised1);
        userRepository.save(u);
        return new ResponseEntity<>(supervised1, HttpStatus.OK);
    }

    public ResponseEntity<Object> getSupervised(int id) {
        return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<Object> create(Supervised supervised) {
        Supervised supervised1 = supervisedRepository.save(supervised);
        return new ResponseEntity<>(supervised1, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllTasks(int id) {
        Supervised supervised = supervisedRepository.findById(id);
        List<Task> allTasks = new ArrayList<Task>();
        List<Project> projects = supervised.getProjects();

        for (Project project : projects) {
            allTasks.addAll(project.getProjects());
        }
        allTasks.sort(new Sortbydeadline());
        return new ResponseEntity<>(allTasks, HttpStatus.OK);
    }

    public ResponseEntity<Object> projectToSupervised(Integer supervisedId, Project project) {
        Supervised supervised = supervisedRepository.findById(supervisedId).get();

        project.setSupervised(supervised);
        Project project1 = projectRepository.save(project);
        supervised.getProjects().add(project1);
        supervisedRepository.save(supervised);
        return new ResponseEntity<>(project,HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllInvites(Integer supervisedId) {
        User u = userRepository.findById(supervisedId).get();
        List<Invitation> invites = u.getInvitations();
        return new ResponseEntity<Object>(invites, HttpStatus.OK);
    }

    //comparator for deadlines
    public static class Sortbydeadline implements Comparator<Task> {
        public int compare(Task a, Task b) {
            return a.getDeadline().compareTo(b.getDeadline());
        }
    }

    public ResponseEntity<Object> addBlacklist(Integer id, String site) {
        Supervised user = supervisedRepository.findById(id).get();
        user.getBlackSiteList().add(new BlackSite(site));
        supervisedRepository.save(user);

        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }


}

