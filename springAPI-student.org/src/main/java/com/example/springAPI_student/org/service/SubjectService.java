package com.example.springAPI_student.org.service;
import com.example.springAPI_student.org.entity.Subject;

import java.util.Optional;

public interface SubjectService {
    Optional<Subject> getSubjectById(Long id);
    Subject addSubject(Subject subject);
    void deleteSubject(Long id);
}

