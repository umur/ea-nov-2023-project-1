package com.dunky.enrollmentservice.service;

import com.dunky.enrollmentservice.payload.EnrollmentDto;

public interface EnrollmentService {
    public EnrollmentDto registerCourse(EnrollmentDto enrollmentDto);
}
