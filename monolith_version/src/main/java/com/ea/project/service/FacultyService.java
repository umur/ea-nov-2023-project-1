package com.ea.project.service;


import com.ea.project.dto.FacultyDto;
import com.ea.project.entity.Faculty;
import com.ea.project.entity.Student;
import com.ea.project.entity.User;

import java.util.List;

public interface FacultyService {
    Faculty findById(Long id);
    FacultyDto update(FacultyDto facultyDto);

    FacultyDto delete(User faculty);
    List<Student> filterStudentsBySearchParam(String state, String city, String major, String name, String id);
}
