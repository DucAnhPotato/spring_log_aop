package com.example.demo_log_aop.repository;

import com.example.demo_log_aop.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByAge(int age);
}
