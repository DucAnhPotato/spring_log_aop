package com.example.demo_log_aop.controller;

import com.example.demo_log_aop.model.Student;
import com.example.demo_log_aop.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping("/new")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }

    @GetMapping()
    public ResponseEntity<Student> getStudentById(@RequestParam Long id) {
        return service.getStudentById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        return service.getAllStudents();
    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,
                                                 @RequestParam Long id) {
        return service.updateStudent(student, id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam Long id) {
        return service.deleteStudent(id);
    }

}
