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
@RequestMapping("/users")  // GET http://localhost:8080/users/name/ernie
public class SpringRestController {
	@Autowired
	private UserJpaRepository repository;
	
	@GetMapping("/all")  // GET http://localhost:8080/users/all
	public List<User> getAllUsers() {
		return repository.findAll(); 
	}
	
	@GetMapping("/{id}")  // GET http://localhost:8080/users/ernie  
	public User getUserById(@PathVariable String id) {
		return repository.findById(id).get(); 
	}
	
	@GetMapping("/name/{userName}")  // GET http://localhost:8080/users/name/ernie
	public User getUserByUserName(@PathVariable String userName) {
		return repository.findByUserName(userName).get(); 
	}
	
	@PostMapping  // POST http://localhost:8080/users
	public User createUser(@RequestBody User user) {
		return repository.save(user); 
	}
	
	@PutMapping   // PUT http://localhost:8080/users
	public User updateUser(@RequestBody User user) {
		return repository.save(user); 
	}
	
	@DeleteMapping("/{id}")  // DELETE http://localhost:8080/users/bigbird
	public void deleteUser(@PathVariable String id) {
		repository.deleteById(id); 
	}

}
