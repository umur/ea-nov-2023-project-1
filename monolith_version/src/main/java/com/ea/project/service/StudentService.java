package com.ea.project.service;

import com.ea.project.dto.StudentDto;
import com.ea.project.entity.Student;
import com.ea.project.entity.User;

public interface StudentService {
    Student findById(Long id);

    StudentDto update(StudentDto studentDto);

    StudentDto delete(User student);

}
