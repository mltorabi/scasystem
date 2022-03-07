package com.example.demo.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> findByStudentId(String studentId);
	
	Boolean existsByStudentId(String studentId);
	
}
