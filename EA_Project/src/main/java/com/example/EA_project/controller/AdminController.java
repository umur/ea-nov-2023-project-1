package com.example.EA_project.controller;

import com.example.EA_project.entity.*;
import com.example.EA_project.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor

public class AdminController {
    private final NewsService newsService;
    private final StudentService studentService;
    private final SurveyService surveyService;
    private final EventService eventService;
    private final JobService jobService;


    /*
Student APIs
 */
    @GetMapping("/student")
    public List<Student> getStudents() {
        return studentService.findAll();
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        studentService.remove(id);
        return ResponseEntity.ok("Successfully deleted student!");
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<String> updateStudent(@RequestBody Student student, @PathVariable int id) {
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
            return ResponseEntity.ok("Successfully updated student!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/student")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        studentService.add(student);
        return ResponseEntity.ok("Successfully added student!");
    }

    @GetMapping("/student/graduation/{gradYear}")
    public List<Student> getStudentByGraduation(@PathVariable String gradYear) {
        return studentService.findByGradution(gradYear);
    }

    @GetMapping("/course/{course}")
    public List<Student> getByCourse(@PathVariable String course) {
        return studentService.findByCourse(course);
    }

    @GetMapping("/location/{city}")
    public List<Student> getByLocation(@PathVariable String city) {
        return studentService.findByLocation(city);
    }

    @GetMapping("/student/industry/{industry}")
    public List<Student> getStudentByIndustry(@PathVariable String industry) {
        return studentService.findByIndustry(industry);
    }


    /*
    Job APIs
     */
    @GetMapping("/job")
    public List<Job> getJob() {
        return jobService.findAll();
    }

    @PostMapping("/job")
    public ResponseEntity<String> addJob(@RequestBody Job job) {
        jobService.add(job);
        return ResponseEntity.ok("Successfully added job! ");
    }

    @PutMapping("/job")
    public ResponseEntity<String> updateJob(@RequestBody Job job) {
        jobService.update(job);
        return ResponseEntity.ok("Successfully updated job! ");

    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable int id) {
        jobService.remove(id);
        return ResponseEntity.ok("Successfully deleted job! ");
    }


    /*
News APIs
 */
    @PostMapping("/news")
    public ResponseEntity<String> addNews(@RequestBody News news) {
        newsService.add(news);
        return ResponseEntity.ok("Successfully added news! ");
    }

    @GetMapping("/news")
    public List<News> getNews() {
        return newsService.findAll();
    }

    @DeleteMapping("/news/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable int id) {
        newsService.remove(id);
        return ResponseEntity.ok("Successfully deleted news!");
    }

    @PutMapping("/news")
    public ResponseEntity<String> updateNews(@RequestBody News news) {
        newsService.update(news);
        return ResponseEntity.ok("Successfully updated news!");

    }



    /*
    Event APIs
     */

    @PostMapping("/event")
    public ResponseEntity<String> addEvent(@RequestBody Event event) {
        eventService.add(event);
        return ResponseEntity.ok("Successfully added event! ");
    }


    @GetMapping("/event")
    public List<Event> getEvent() {
        return eventService.findAll();
    }

    @DeleteMapping("event/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable int id) {
        eventService.remove(id);
        return ResponseEntity.ok("Successfully deleted an event! ");
    }

    @PutMapping("/event")
    public ResponseEntity<String> updateEvent(@RequestBody Event Event) {
        eventService.update(Event);
        return ResponseEntity.ok("Successfully updated an event! ");
    }

    @PostMapping("event/rsvp/{event_id}")
    public ResponseEntity<String> RSVP(@PathVariable int event_id, @RequestBody Student student) {
        eventService.RSVP(event_id, student);
        return ResponseEntity.ok("Successfully RSVP an event! ");

    }



    /*
    Survey APIs
     */

    @PostMapping("/survey")
    public ResponseEntity<String> addSurvey(@RequestBody Survey survey) {
        surveyService.add(survey);
        return ResponseEntity.ok("Successfully added a survey! ");
    }


    @GetMapping("/survey")
    public List<Survey> getSurvey() {
        return surveyService.findAll();
    }


    @PutMapping("/survey")
    public ResponseEntity<String> updateSurvey(@RequestBody Survey survey) {
        surveyService.update(survey);
        return ResponseEntity.ok("Successfully updated a survey! ");

    }

    @DeleteMapping("survey/{id}")
    public ResponseEntity<String> deleteSurvey(@PathVariable int id) {
        surveyService.remove(id);
        return ResponseEntity.ok("Successfully deleted a survey! ");
    }

}
