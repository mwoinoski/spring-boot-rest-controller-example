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
		return repository.findById(id).get(); 
	}
	
	@GetMapping("/name/{userName}")
	public User getUserByUserName(@PathVariable String userName) {
		return repository.findByUserName(userName).get(); 
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return repository.save(user); 
	}
	
	@PutMapping
	public User updateUser(@RequestBody User user) {
		return repository.save(user); 
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable String id) {
		repository.deleteById(id); 
	}

}
