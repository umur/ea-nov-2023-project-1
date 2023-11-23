package com.alumni.courseservice.payload;

import com.alumni.courseservice.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseStatus {
    private String status; // pending, progress, completed
    private String message;
    private Course course;
}
