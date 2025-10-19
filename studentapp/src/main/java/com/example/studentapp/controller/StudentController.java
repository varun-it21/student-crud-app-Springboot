package com.example.studentapp.controller;

import com.example.studentapp.model.student;
import com.example.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public List<student> getAllStudents() {  // List with capital L
        return service.getAllStudents();     // no dot after return
    }

    @GetMapping("/{id}")
    public student getStudent(@PathVariable int id) {
        return service.getStudentById(id);
    }

    @PostMapping
    public student addStudent(@RequestBody student student) {
        return service.addStudent(student);
    }

    @PutMapping("/{id}")
    public student updateStudent(@PathVariable int id, @RequestBody student student) {
        return service.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        return service.deleteStudent(id);
    }
}
