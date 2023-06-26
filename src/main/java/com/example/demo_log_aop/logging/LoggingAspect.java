package com.example.demo_log_aop.logging;

import com.example.demo_log_aop.model.Request;
import com.example.demo_log_aop.service.RequestService;
import com.example.demo_log_aop.utils.URLBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.UUID;

@Aspect
@Slf4j
@Configuration
public class LoggingAspect {
    @Autowired
    private RequestService requestService;

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMapping() {}
    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {}
    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {}
    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deleteMapping() {}
    @Pointcut("(putMapping() || postMapping() || deleteMapping() || getMapping())")
    public void commonPointCut() {}
    @Pointcut("execution(* *(..))")
    public void atExecution() {}

    @Around(value = "commonPointCut() && atExecution()")
    public Object loggingTest(ProceedingJoinPoint pjp) {
        Request logRequest = new Request();
        Object response = null;

        try {
            HttpServletRequest httpRequest = ((ServletRequestAttributes)
                    RequestContextHolder.currentRequestAttributes()).getRequest();

            Object[] request = pjp.getArgs();
            String url = URLBuilder.build(httpRequest);
            String serviceName = pjp.getSignature().getName();
            response = pjp.proceed();

            logRequest.setRequestId(UUID.randomUUID().toString().split("-")[0]);
            logRequest.setTime(LocalDateTime.now().toString());
            logRequest.setRequest(request);
            logRequest.setRequestURL(url);
            logRequest.setServiceName(serviceName);
            logRequest.setResponse(response);
        } catch (Throwable ignored) {

        } finally {
            requestService.addRequest(logRequest);
        }

        return response;
    }
}
