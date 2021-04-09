package com.satrabench.getfriends.repository;

import com.satrabench.getfriends.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation,Integer>{
    Invitation findById(int id);
}
