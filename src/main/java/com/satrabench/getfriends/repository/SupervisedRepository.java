package com.satrabench.getfriends.repository;

import com.satrabench.getfriends.model.Supervised;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisedRepository extends JpaRepository<Supervised, Integer> {
    Supervised findByNameAndPassword(String user, String password);
    Supervised findById(int id);

}
