package com.example.demo_log_aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DemoLogAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoLogAopApplication.class, args);
    }

}
