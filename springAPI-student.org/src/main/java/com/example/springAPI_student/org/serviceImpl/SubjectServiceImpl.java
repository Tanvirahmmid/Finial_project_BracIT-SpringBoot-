package com.example.springAPI_student.org.serviceImpl;
import com.example.springAPI_student.org.entity.Subject;
import com.example.springAPI_student.org.repository.SubjectRepository;
import com.example.springAPI_student.org.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }



    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}


