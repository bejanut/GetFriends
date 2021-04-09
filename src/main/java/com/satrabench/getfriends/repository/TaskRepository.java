package com.satrabench.getfriends.repository;

import com.satrabench.getfriends.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
}