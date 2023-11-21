package com.dunky.enrollmentservice.controller;

import com.dunky.enrollmentservice.entity.Enrollment;
import com.dunky.enrollmentservice.payload.CourseDto;
import com.dunky.enrollmentservice.payload.UserDto;
import com.dunky.enrollmentservice.repository.EnrollmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
        private final EnrollmentRepository enrollmentRepo;
        private final WebClient webClient;


        public EnrollmentController(EnrollmentRepository repository, WebClient webClient) {
            this.enrollmentRepo = repository;
            this.webClient = webClient;
        }

        @PostMapping
        public ResponseEntity<Enrollment> enrollUser(@RequestBody Enrollment enrollment) {
            // Validate user and course existence
            if (isValidUser(enrollment.getUserId()) && isValidCourse(enrollment.getCourseId())) {
                return ResponseEntity.ok(enrollmentRepo.save(enrollment));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        private boolean isValidUser(Long userId) {
            UserDto userDto = webClient.get()
                    .uri("http://localhost:8802/api/users/" + userId)
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .block();

            return webClient.get().equals(HttpStatus.OK) ;
        }

        private boolean isValidCourse(Long courseId) {
            CourseDto courseDto = webClient.get()
                    .uri("http://localhost:8808/api/courses/" + courseId)
                    .retrieve()
                    .bodyToMono(CourseDto.class)
                    .block();

            return webClient.get().equals(HttpStatus.OK) ;
        }


    }

