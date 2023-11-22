package com.alumni.courseservice.controller;

import com.alumni.courseservice.payload.CourseDto;
import com.alumni.courseservice.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Get a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    // Build Save Course REST API
    @PostMapping
    public ResponseEntity<CourseDto> saveOrganization(@RequestBody CourseDto courseDto){
        CourseDto savedCourse = courseService.saveCourse(courseDto);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    // Build Get Course by Code REST API
    @GetMapping("{code}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable("code") String courseCode){
        CourseDto courseDto = courseService.getCourseByCode(courseCode);
        return ResponseEntity.ok(courseDto);
    }

}
