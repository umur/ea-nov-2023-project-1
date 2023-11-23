package com.project.business.controller;

import com.project.business.dto.get.GetAlumniDto;
import com.project.business.dto.get.GetFullAlumniDto;
import com.project.business.dto.get.GetFullOpenJobDto;
import com.project.business.dto.post.PostOpenJobDto;
import com.project.business.service.OpenJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/open_jobs")
@RequiredArgsConstructor
public class OpenJobController {
    private final OpenJobService openJobService;


    @PostMapping
    public GetFullOpenJobDto addOpenJob(@RequestBody PostOpenJobDto openJobDto, @RequestHeader("mail") String mail) {
        return openJobService.addOpenJob(openJobDto, mail);
    }

    @GetMapping
    public List<GetFullOpenJobDto> getAllOpenJobs() {
        return openJobService.getAll();
    }

    @GetMapping("/{id}")
    public GetFullOpenJobDto getOpenJobById(@PathVariable Long id) {
        try {
            return openJobService.getById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Open Job not found");
        }
    }

    @GetMapping("/{id}/applicants")
    public List<GetAlumniDto> getApplicants(@PathVariable Long id, @RequestHeader("mail") String mail) {
        try {
            return openJobService.getApplicants(id, mail);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Open Job not found");
        }
    }

    @GetMapping("/searchByState")
    public List<GetFullOpenJobDto> searchByState(@RequestParam String state) {
        try {
            return openJobService.searchByState(state);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Open Job not found");
        }
    }

    @GetMapping("/searchByCity")
    public List<GetFullOpenJobDto> searchByCity(@RequestParam String city) {
        try {
            return openJobService.searchByCity(city);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Open Job not found");
        }
    }

    @GetMapping("/searchByCompanyName")
    public List<GetFullOpenJobDto> searchByCompanyName(@RequestParam String companyName) {
        try {
            return openJobService.searchBycompanyName(companyName);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Open Job not found");
        }
    }

    @PutMapping("/{id}")
    public GetFullOpenJobDto updateById(@PathVariable Long id,
                                        @RequestBody PostOpenJobDto postFullOpenJobDto,
                                        @RequestHeader("mail") String mail) {
        try {
            return openJobService.updateById(id, postFullOpenJobDto, mail);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Open Job not found");
        }
    }

    @DeleteMapping("/{id}")
    public GetFullOpenJobDto deleteById(@PathVariable Long id) {
        try {
            return openJobService.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Open Job not found");
        }
    }
}
