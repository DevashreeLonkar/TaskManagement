package com.task.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.task.management.entity.User;
import com.task.management.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping({"/createUser"})
	public User createUser(@RequestBody User user) {
	return userService.createUser(user);
	}
	
	@GetMapping({"/getAllUsers"})
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping({"/getUserById/{id}"})
	public User getUserById(@PathVariable("id") Long id) {
		return userService.getUserById(id);
	}
	
	@PutMapping({"/updateUserById/{id}"})
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) throws Exception {
		return userService.updateUserById(id, user);
	}
	
	@DeleteMapping({"/deleteUserById/{id}"})
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
}
