package com.example.demo_log_aop.service;

import com.example.demo_log_aop.model.Student;
import com.example.demo_log_aop.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repo;

    public ResponseEntity<Student> addStudent(Student student) {
        return new ResponseEntity<>(repo.save(student), HttpStatus.CREATED);
    }

    public ResponseEntity<Student> getStudentById(Long id) {
        if (repo.existsById(id)) {
            return new ResponseEntity<>(repo.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Student> updateStudent(Student student, Long id)  {
        Student preStudent = getStudentById(id).getBody();

        if (preStudent != null) {
            preStudent.setName(student.getName());
            preStudent.setAge(student.getAge());
            return new ResponseEntity<>(repo.save(preStudent), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteStudent(Long id) {
        Student student = getStudentById(id).getBody();

        if (student != null) {
            repo.deleteById(id);
            return new ResponseEntity<>("Delete student success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete student failed", HttpStatus.BAD_REQUEST);
    }
}
