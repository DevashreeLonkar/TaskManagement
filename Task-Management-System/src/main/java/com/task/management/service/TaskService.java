package com.task.management.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.management.Response.TaskResponse;
import com.task.management.entity.Task;
import com.task.management.entity.User;
import com.task.management.enums.Status;
import com.task.management.repository.TaskRepository;
import com.task.management.repository.UserRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Task createTask(Task task, Long id) throws Exception {
		User user= userRepository.findById(id)
				.orElseThrow(()-> new Exception("User not found"));
		
		 task.setUser(user);
		 return taskRepository.save(task);
	}
	
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}
	
	public Task getTaskById(Long task_id) {
	return taskRepository.findById(task_id).get();
	}

	public String updateTask(Long id,Status status) {
		Optional<Task> task = taskRepository.findById(id);
		if(task.isPresent()) {
			task.get().setStatus(status);
			taskRepository.save(task.get());
			return "Status changed !!";
		}
		return "Task id is not Present !!";
	}

	public void deleteTaskById(Long id) {
		taskRepository.deleteById(id);		
	}

	public Task getTaskByUserId(Long id) {
		return taskRepository.findByUserId(id).get();
	}

	public List<Task> getTaskByStatus(Status status) {
		return taskRepository.findByStatus(status);
	}

	public List<TaskResponse> findAll() {
		List<Task> tasks = taskRepository.findAll();
		return TaskResponse.init(tasks);
	}
	
	public TaskResponse getTaskByUserIdNew(Long id) {
		return TaskResponse.init(taskRepository.findByUserId(id).get());
	}
}
