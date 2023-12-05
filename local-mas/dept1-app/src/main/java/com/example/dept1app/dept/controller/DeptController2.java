package com.example.dept1app.dept.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DeptController2 {

    private Logger logger = LoggerFactory.getLogger(DeptController2.class);

    @GetMapping("/test")
    //@CircuitBreaker(name = "test", fallbackMethod = "hardcodedResponse")
    @Bulkhead(name = "test", fallbackMethod = "hardcodedResponse")
    public String test(){
        logger.info("test call received");
        ResponseEntity<String> result = new RestTemplate()
                .getForEntity("http://localhost:8080/error", String.class);

        return result.getBody();
    }

    public String hardcodedResponse(Exception e){
        //logger.error("ERROR : " + e.getMessage());
        return "error-response";
    }
}
