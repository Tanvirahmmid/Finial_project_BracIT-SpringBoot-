package com.example.springAPI_student.org.service;
import com.example.springAPI_student.org.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface StudentService {
    Page<Student> getStudents(Pageable pageable);
    Page<Student> searchStudents(String name, Long id, Pageable pageable);
    Optional<Student> getStudentById(Long id);
    Student addStudent(Student student);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
}
