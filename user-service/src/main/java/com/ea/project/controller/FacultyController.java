package com.ea.project.controller;

import com.ea.project.aop.LogMe;
import com.ea.project.dto.FacultyDto;
import com.ea.project.dto.StudentDto;
import com.ea.project.entity.User;
import com.ea.project.service.impl.FacultyServiceImpl;
import com.ea.project.service.impl.StudentServiceImpl;
import com.ea.project.util.Mapper;
import com.ea.project.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/faculties")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PreAuthorize("hasAuthority('FACULTY')")
public class FacultyController {
    private final FacultyServiceImpl facultyServiceImpl;
    private final StudentServiceImpl studentService;
    private final SecurityUtil securityUtil;

    private final Mapper mapper;

    @LogMe
    @PutMapping
    public FacultyDto update(@RequestBody FacultyDto facultyDto) {
        return facultyServiceImpl.update(facultyDto);
    }

    @DeleteMapping
    public FacultyDto delete() {
        User currentFaculty = securityUtil.getCurrentUser();
        return facultyServiceImpl.delete(currentFaculty);
    }

    @GetMapping("/filter/students")
    public ResponseEntity<?> filterStudentsByParam(@RequestParam(value = "state", required = false) String state, @RequestParam(value = "city", required = false) String city, @RequestParam(value = "major", required = false) String major, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "id", required = false) String id) {
        List<StudentDto> result = new ArrayList<>();
        try {
            result.addAll(facultyServiceImpl.filterStudentsBySearchParam(state, city, major, name, id).stream().map(mapper::mapStudentToDTO).toList());
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }

        return ResponseEntity.ok(result);
    }


}
