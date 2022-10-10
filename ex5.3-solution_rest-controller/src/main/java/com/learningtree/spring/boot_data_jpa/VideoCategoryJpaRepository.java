package com.learningtree.spring.boot_data_jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoCategoryJpaRepository extends JpaRepository<VideoCategory, Integer> {
	Optional<VideoCategory> findByName(String categoryName);
}
