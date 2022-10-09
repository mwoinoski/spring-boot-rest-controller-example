package com.learningtree.spring.rest;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
		return repository.findAll(); 
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable String id) {
		try {
			return repository.findById(id).orElseThrow(
					() -> new ServerWebInputException("unknown user " + id)); // HTTP status 400
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("error retrieving user " + id, e);  // HTTP status 500
		} 
	}
	
	@GetMapping("/name/{userName}")
	public User getUserByUserName(@PathVariable String userName) {
		try {
			return repository.findByUserName(userName).orElseThrow(
					() -> new ServerWebInputException("unknown user " + userName));
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("error retrieving user " + userName, e);
		} 
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		try {
			return repository.save(user);
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("error saving user " + user, e);
		} 
	}
	
	@PutMapping
	public User updateUser(@RequestBody User user) {
		try {
			return repository.save(user);
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("error updating user " + user, e);
		} 
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable String id) {
		try {
			repository.deleteById(id);
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("error deleting user id " + id, e);
		} 
	}

}
