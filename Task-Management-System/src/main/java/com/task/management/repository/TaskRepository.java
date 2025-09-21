package com.task.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.management.entity.Task;
import com.task.management.entity.User;
import com.task.management.enums.Status;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	Optional<Task> findByUserId(Long id);

	List<Task> findByStatus(Status status);

	
}
