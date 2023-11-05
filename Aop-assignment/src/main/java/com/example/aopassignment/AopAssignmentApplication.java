package com.example.aopassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopAssignmentApplication.class, args);
    }


    @Bean
    public ModelMapper modelMapper()
    {
        return  new ModelMapper();
    }
}
