package com.example.demo_log_aop.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Document(collection = "request")
@Data
public class Request {
    @Id
    private String requestId;
    private Object[] request;
    private Object response;
    private String serviceName;
    private String requestURL;
    private String time;
    private String status;
}
