package com.example.demo_log_aop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id", nullable = false)
    private Long id;
    @Column(name = "s_name")
    private String name;
    @Column(name = "s_age")
    private int age;

}
