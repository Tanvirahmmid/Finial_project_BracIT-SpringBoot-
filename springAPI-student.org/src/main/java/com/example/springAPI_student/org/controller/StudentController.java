package com.example.springAPI_student.org.controller;


import com.example.springAPI_student.org.entity.Student;
import com.example.springAPI_student.org.entity.Subject;
import com.example.springAPI_student.org.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Page<Student> getStudents(@RequestParam int page,
                                     @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentService.getStudents(pageable);
    }
    @GetMapping("/search")
    public Page<Student> searchStudents(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) Long id,
                                        @RequestParam int page,
                                        @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentService.searchStudents(name, id, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> existingStudent = studentService.getStudentById(id);
        if (existingStudent.isPresent()) {
            return ResponseEntity.ok(studentService.updateStudent(id, student));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/subjects")
    public ResponseEntity<Student> addSubjectToStudent(@PathVariable Long id, @RequestBody Subject subject) {
        Optional<Student> studentOpt = studentService.getStudentById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.getSubjects().add(subject);
            studentService.updateStudent(id, student);
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/subjects/{subjectId}")
    public ResponseEntity<Student> removeSubjectFromStudent(@PathVariable Long id, @PathVariable Long subjectId) {
        Optional<Student> studentOpt = studentService.getStudentById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.getSubjects().removeIf(subject -> subject.getId().equals(subjectId));
            studentService.updateStudent(id, student);
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
