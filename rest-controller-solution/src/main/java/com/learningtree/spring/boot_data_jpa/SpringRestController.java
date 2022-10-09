package com.learningtree.spring.boot_data_jpa;

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
@RequestMapping("/video-categories")
public class SpringRestController {
	@Autowired
	private VideoCategoryJpaRepository repository;
	
	@GetMapping("/all")
	public List<VideoCategory> getAllVideoCategories() {
		return repository.findAll(); // sets HTTP status 200 by default
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VideoCategory> getVideoCategoryById(@PathVariable int id) {
		try {
			return repository.findById(id)
							 .map(category -> ResponseEntity.ok(category))  // HTTP status 200
							 .orElse(ResponseEntity.noContent().build());  // HTTP status 204
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("error retrieving category " + id, e);
		} 
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<VideoCategory> getVideoCategoryByName(@PathVariable String name) {
		try {
			return repository.findByName(name)
					 		 .map(category -> ResponseEntity.ok(category)) // or map(ResponseEntity::ok)
					 		 .orElse(ResponseEntity.noContent().build());
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("error retrieving category " + name, e);
		} 
	}
	
	@PostMapping
	public ResponseEntity<VideoCategory> createVideoCategory(@RequestBody VideoCategory category) {
		if (category.getName() == null || category.getName().isBlank()) {
			throw new ServerWebInputException("category name is empty");
		}

		try {
			VideoCategory savedVideoCategory = repository.save(category);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedVideoCategory);
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("error saving category " + category, e);
		} 
	}
	
	@PutMapping
	public ResponseEntity<VideoCategory> updateVideoCategory(@RequestBody VideoCategory category) {
		if (category.getId() < 1) {
			throw new ServerWebInputException("invalid category id " + category.getId());
		}
		if (category.getName() == null || category.getName().isBlank()) {
			throw new ServerWebInputException("category name is empty");
		}
		if (!repository.existsById(category.getId())) {
			throw new ServerWebInputException("unknown category " + category.getName());
		}

		try {
			VideoCategory updatedVideoCategory = repository.save(category);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedVideoCategory);
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("error updating category " + category, e);
		} 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVideoCategory(@PathVariable int id) {
		if (id < 1) {
			throw new ServerWebInputException("invalid category id " + id);
		}
		if (!repository.existsById(id)) {
			throw new ServerWebInputException("unknown category " + id);
		}
		try {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();  // HTTP status 204
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("error deleting category id " + id, e);
		} 
	}

}
