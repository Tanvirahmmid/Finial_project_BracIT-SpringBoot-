package com.example.springAPI_student.org.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String roll;
    private String email;
    private LocalDate dateOfBirth;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "student_id")
    private List<Subject> subjects = new ArrayList<>();

    public Student() {
    }

    public Student(String name, String roll, String email, LocalDate dateOfBirth, List<Subject> subjects) {
        this.name = name;
        this.roll = roll;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.subjects = subjects;
    }

    public Student(String name, String roll, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.roll = roll;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Student(Long id, String name, String roll, String email, LocalDate dateOfBirth, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.roll = roll;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.subjects = subjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
