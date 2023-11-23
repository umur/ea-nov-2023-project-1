package com.alumni.jobservice.Config;

import com.alumni.jobservice.Service.Impl.JobServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
    @Autowired
    JobServiceImpl service;

    public void receiveMessage(String message) {
        System.out.println(message);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Message response = mapper.readValue(message, Message.class);
            service.activatePosterJobs(response.getUser_id(), response.isShow());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}