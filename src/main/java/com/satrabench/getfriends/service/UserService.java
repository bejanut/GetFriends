package com.satrabench.getfriends.service;

import com.satrabench.getfriends.model.Supervised;
import com.satrabench.getfriends.model.User;
import com.satrabench.getfriends.repository.SupervisedRepository;
import com.satrabench.getfriends.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final SupervisedRepository supervisedRepository;

    @Autowired
	public UserService(UserRepository userRepository,
		    SupervisedRepository supervisedRepository) {
		this.userRepository = userRepository;
	    this.supervisedRepository = supervisedRepository;
    }

	public ResponseEntity<Object> getAll(){
    	List<User> users = userRepository.findAll();
		return new ResponseEntity<Object>(users, HttpStatus.OK);
}

	public ResponseEntity<Object> create(User user){
    	User u = userRepository.save(user);
		return new ResponseEntity<Object>(u, HttpStatus.OK);
	}

	public ResponseEntity<Object> authentication(String name, String password) {
    	User user = userRepository.findByNameAndPassword(name,password);
		Supervised supervised=null;
    	if(user == null)
		supervised = supervisedRepository.findByNameAndPassword(name,password);
    	if(supervised!=null) new ResponseEntity<Object>(supervised, HttpStatus.OK);
		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}

}
