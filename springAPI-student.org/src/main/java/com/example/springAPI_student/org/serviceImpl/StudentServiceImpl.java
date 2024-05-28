package com.example.springAPI_student.org.serviceImpl;


import com.example.springAPI_student.org.entity.Student;
import com.example.springAPI_student.org.repository.StudentRepository;
import com.example.springAPI_student.org.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Page<Student> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
    @Override
    public Page<Student> searchStudents(String name, Long id, Pageable pageable) {
        if (name != null && id != null) {
            return studentRepository.findByNameContainingAndId(name, id, pageable);
        } else if (name != null) {
            return studentRepository.findByNameContaining(name, pageable);
        } else if (id != null) {
            return studentRepository.findById(id, pageable);
        } else {
            return studentRepository.findAll(pageable);
        }
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
