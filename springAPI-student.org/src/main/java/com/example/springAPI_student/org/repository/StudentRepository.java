package com.example.springAPI_student.org.repository;

import com.example.springAPI_student.org.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findByNameContaining(String name, Pageable pageable);
    Page<Student> findById(Long id, Pageable pageable);  // This needs to return a single page with the found student
    Page<Student> findByNameContainingAndId(String name, Long id, Pageable pageable);
}


