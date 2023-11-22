package com.alumni.JobService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alumni.JobService.dto.PostingDto;
import com.alumni.JobService.service.PostingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/postings")
@RequiredArgsConstructor
public class PostingsController {
    private final PostingService postingService;

    @GetMapping
    private List<PostingDto> findAll() {
        return postingService.findAll();
    }

    @GetMapping("{id}")
    private PostingDto findById(@PathVariable Long id) {
        return postingService.findById(id);
    }

    @PostMapping
    private ResponseEntity<HttpStatus> save(@RequestBody PostingDto newPosting) {
        postingService.save(newPosting);

        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    private ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody PostingDto updatedPosting) {
        postingService.update(id, updatedPosting);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
