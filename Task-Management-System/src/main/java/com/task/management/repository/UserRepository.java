package com.task.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.management.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
