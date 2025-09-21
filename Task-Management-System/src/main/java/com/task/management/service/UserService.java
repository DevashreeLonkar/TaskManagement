package com.task.management.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.management.entity.User;
import com.task.management.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {
		Set<User> users= new HashSet<>();
		users.add(user);
		return userRepository.save(user);
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public User updateUserById(Long id, User updatedUser) throws Exception {
		return userRepository.findById(id)
				.map(existingUser-> {
					existingUser.setName(updatedUser.getName());
					existingUser.setEmail(updatedUser.getEmail());
					return userRepository.save(existingUser);
				})
				.orElseThrow(() -> new RuntimeException("User not found with " +id));
	}
	
	public void deleteUserById(Long id) {
		 userRepository.deleteById(id);
	}
	 
}
