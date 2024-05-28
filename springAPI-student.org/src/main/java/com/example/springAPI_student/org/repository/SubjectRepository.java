package com.example.springAPI_student.org.repository;
import com.example.springAPI_student.org.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}

