package com.ea.project.controller;

import com.ea.project.dto.ExperienceDto;
import com.ea.project.dto.StudentDto;
import com.ea.project.entity.Experience;
import com.ea.project.entity.User;
import com.ea.project.service.impl.ExperienceServiceImpl;
import com.ea.project.service.impl.StudentServiceImpl;
import com.ea.project.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {
    private final StudentServiceImpl studentServiceImpl;
    private final SecurityUtil securityUtil;
    private final ExperienceServiceImpl experienceService;

    @PutMapping
    public StudentDto update(@RequestBody StudentDto studentDto) {
        return studentServiceImpl.update(studentDto);
    }

    @DeleteMapping
    public StudentDto delete() {
        User currentStudent = securityUtil.getCurrentUser();
        return studentServiceImpl.delete(currentStudent);
    }

    @PutMapping("/experience")
    public ResponseEntity<?> addExperience(@RequestBody ExperienceDto experienceDto) {
        User currentStudent;
        try {
            currentStudent = securityUtil.getCurrentUser();
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        Experience experience = new Experience();
        experience.setStudent(currentStudent);
        experience.setTitle(experienceDto.getTitle());
        experience.setFrom(experienceDto.getStarDate());
        experience.setTo(experienceDto.getEndDate());
        experience.setCompany(experienceDto.getCompany());
        return ResponseEntity.ok(experienceService.create(experience));
    }

}
