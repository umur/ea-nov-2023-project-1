package com.example.EA_project.controller;

import com.example.EA_project.entity.Student;
import com.example.EA_project.service.StudentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<Student> getStudent() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        studentService.add(student);
        return ResponseEntity.ok("Successfully added student");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@RequestBody Student student, @PathVariable int id){
        Student existingStudent = studentService.findById(id);
        if (existingStudent != null) {
            existingStudent.setGraduationYear(student.getGraduationYear());
            existingStudent.setDescription(student.getDescription());
            existingStudent.setCategory(student.getCategory());
            existingStudent.setIndustry(student.getIndustry());
            existingStudent.setAddress(student.getAddress());
            existingStudent.setCourses(student.getCourses());
            existingStudent.setJobs(student.getJobs());
            existingStudent.setUser(student.getUser());
            studentService.update(existingStudent);
            return ResponseEntity.ok("Successfully updated student");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
        studentService.remove(id);
        return ResponseEntity.ok("Successfully deleted student");
    }


    @GetMapping("/graduation/{gradYear}")
    public List<Student> getStudentByGraduation(@PathVariable String gradYear) {
        return studentService.findByGradution(gradYear);
    }
    @GetMapping("/course/{course}")
    public List<Student> getStudentByCourse(@PathVariable String course) {
        return studentService.findByCourse(course);
    }

    @GetMapping("/industry/{industry}")
    public List<Student> getStudentByIndustry(@PathVariable String industry) {
        return studentService.findByIndustry(industry);
    }

    @GetMapping("/location/{city}")
    public List<Student> getStudentByLocation(@PathVariable String city) {
        return studentService.findByLocation(city);
    }





}
