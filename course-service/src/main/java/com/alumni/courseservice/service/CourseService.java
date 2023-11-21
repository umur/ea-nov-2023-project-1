package com.alumni.courseservice.service;

import com.alumni.courseservice.payload.CourseDto;

public interface CourseService {
    public CourseDto getCourseById(Long id);
}
