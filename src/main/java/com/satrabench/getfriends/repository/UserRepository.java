package com.satrabench.getfriends.repository;

import com.satrabench.getfriends.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	User findByNameAndPassword(String user, String password);
	User findById(int id);
}
