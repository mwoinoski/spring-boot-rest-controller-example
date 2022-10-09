package com.learningtree.spring.rest;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.server.ServerWebInputException;

@RestController
@Transactional
@RequestMapping("/users")
public class SpringRestController {
	@Autowired
	private UserJpaRepository repository;
	
	@GetMapping("/all")
	public List<User> getAllUsers() {
		return repository.findAll(); // sets HTTP status 200 by default
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable String id) {
		try {
			return repository.findById(id)
							 .map(user -> ResponseEntity.ok(user))  // HTTP status 200
							 .orElse(ResponseEntity.noContent().build());  // HTTP status 204
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("error retrieving user " + id, e);
		} 
	}
	
	@GetMapping("/name/{userName}")
	public ResponseEntity<User> getUserByUserName(@PathVariable String userName) {
		try {
			return repository.findByUserName(userName)
					 		 .map(user -> ResponseEntity.ok(user)) // or map(ResponseEntity::ok)
					 		 .orElse(ResponseEntity.noContent().build());
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("error retrieving user " + userName, e);
		} 
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		if (user.getUserName() == null || user.getUserName().isBlank()) {
			throw new ServerWebInputException("user name is empty");
		}
		if (user.getUserPass() == null || user.getUserPass().isBlank()) {
			throw new ServerWebInputException("user password is empty");
		}

		try {
			User savedUser = repository.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("error saving user " + user, e);
		} 
	}
	
	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		if (user.getUserName() == null || user.getUserName().isBlank()) {
			throw new ServerWebInputException("user name is empty");
		}
		if (!repository.existsById(user.getUserName())) {
			throw new ServerWebInputException("unknown user " + user.getUserName());
		}

		try {
			User updatedUser = repository.save(user);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedUser);
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("error updating user " + user, e);
		} 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		if (id == null || id.isBlank()) {
			throw new ServerWebInputException("user name is empty");
		}
		if (!repository.existsById(id)) {
			throw new ServerWebInputException("unknown user " + id);
		}
		try {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();  // HTTP status 204
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("error deleting user id " + id, e);
		} 
	}

}
