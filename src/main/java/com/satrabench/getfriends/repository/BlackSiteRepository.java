package com.satrabench.getfriends.repository;

import com.satrabench.getfriends.model.BlackSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackSiteRepository extends JpaRepository<BlackSite,Integer>{
}
