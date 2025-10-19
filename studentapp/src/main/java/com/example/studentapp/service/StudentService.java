package com.example.studentapp.service;

import com.example.studentapp.model.student;
import com.example.studentapp.model.student;
import com.example.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    // Get all students
    public List<student> getAllStudents() {
        return repo.findAll();
    }
    // Get one student by ID
    public student getStudentById(int id){
        return repo.findById(id).orElse(null);
    }

    // Add new student
    public student addStudent(student student){
        return repo.save(student);
    }

    // Update existing student
    public student updateStudent(int id, student newStudent) {
        student existing = repo.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(newStudent.getName());
            existing.setEmail(newStudent.getEmail());
            existing.setAge(newStudent.getAge());
            return repo.save(existing);
        }
        return null;
    }

    // Delete student
    public String deleteStudent(int id) {
        repo.deleteById(id);
        return "Student deleted successfully!";
    }
}
