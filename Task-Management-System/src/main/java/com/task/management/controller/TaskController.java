package com.task.management.controller;

import java.security.Identity;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.management.Response.TaskResponse;
import com.task.management.entity.Task;
import com.task.management.entity.User;
import com.task.management.enums.Status;
import com.task.management.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping({"/createTask/{id}"})
	public Task createTask(@PathVariable Long id, @RequestBody Task task) throws Exception {
		return taskService.createTask(task, id);
	}
	
	@GetMapping({"/getAllTasks"})
	public List<Task> getAllTasks() {
		return taskService.getAllTasks();
	}
	
	@GetMapping({"/getTaskById/{id}"})
	public Task getTaskById(@PathVariable("id") Long task_id) {
		return taskService.getTaskById(task_id);
	}
	
	@PutMapping("/updateTask/{id}")
	public String updateTask(@PathVariable Long id,@RequestParam Status status) {
		return taskService.updateTask(id,status);
	}
	
	@DeleteMapping("/deleteTaskById/{id}")
	public void deleteTaskById(@PathVariable Long id) {
		taskService.deleteTaskById(id);
	}
	
	@GetMapping("/getTaskByUserId/{id}")
	public Task getTaskByUserId(@PathVariable Long id) {
		return taskService.getTaskByUserId(id);
	}
	
	@GetMapping("/getTaskByStatus/{status}")
	public List<Task> getTaskByStatus(@PathVariable Status status) {
		return taskService.getTaskByStatus(status);
	}
	
	@GetMapping("/fetchRecords")
	public List<TaskResponse> fetchRecords() {
		return taskService.findAll();
	}

	@GetMapping("/getTaskByUserIdNew/{id}")
	public TaskResponse getTaskByUserIdNew(@PathVariable Long id) {
		return taskService.getTaskByUserIdNew(id);
	}
}
