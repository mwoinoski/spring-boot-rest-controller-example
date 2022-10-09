package com.learningtree.spring.rest;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, String> {
	// Define methods to search by a User's property values
	// Syntax: Optional<User> findBy[property-name](...);
	Optional<User> findByUserName(String userName);

	// Auto-generated methods:
	// 	Optional<User> findById(String id);
	// 	List<User> findAll();  // changes are automatically persisted
	//  boolean existsById(String  id);
	// 	long count();
	// 	User save(User user);  // saves or updates
	// 	List<User> saveAll(List<User> users);
	// 	void delete(User user);
	// 	void deleteAllById(String id);
}
