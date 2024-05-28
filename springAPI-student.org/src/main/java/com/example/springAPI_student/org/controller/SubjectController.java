package com.example.springAPI_student.org.controller;
import com.example.springAPI_student.org.entity.Student;
import com.example.springAPI_student.org.entity.Subject;
import com.example.springAPI_student.org.service.StudentService;
import com.example.springAPI_student.org.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/{studentId}")
    public ResponseEntity<Subject> addSubjectToStudent(@PathVariable Long studentId, @RequestBody Subject subject) {
        Optional<Student> studentOpt = studentService.getStudentById(studentId);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            subject = subjectService.addSubject(subject);
            student.getSubjects().add(subject);
            studentService.updateStudent(studentId, student);
            return ResponseEntity.ok(subject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        Optional<Subject> subject = subjectService.getSubjectById(id);
        return subject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }
}
