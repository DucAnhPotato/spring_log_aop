package com.example.demo_log_aop.service;

import com.example.demo_log_aop.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RequestService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void addRequest(Request request) {
        mongoTemplate.insert(request);
    }
}
