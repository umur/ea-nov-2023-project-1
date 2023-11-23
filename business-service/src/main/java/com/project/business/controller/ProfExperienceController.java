package com.project.business.controller;

import com.project.business.dto.get.GetProfExperienceDto;
import com.project.business.service.ProfExperienceService;
import jakarta.ws.rs.NotAllowedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/prof_experiences")
@RequiredArgsConstructor
public class ProfExperienceController {
    private final ProfExperienceService profExperienceService;


    @GetMapping("/{id}")
    public GetProfExperienceDto getProfExperienceById(@PathVariable Long id) {
        try {
            return profExperienceService.getById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prof experience not found");
        }
    }

    @GetMapping("/alumni/{alumniId}")
    public List<GetProfExperienceDto> getProfExperienceByAlumniId(@PathVariable Long alumniId) {
        try {
            return profExperienceService.getByAlumniId(alumniId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prof experience not found");
        }
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id, @RequestHeader("mail") String mail) {
        try {
            profExperienceService.deleteById(id, mail);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ProfExperience not found");
        } catch (NotAllowedException e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        }
    }

    @GetMapping("/searchByIndustry/{industry}")
    public List<GetProfExperienceDto> searchByIndustry(@PathVariable String industry) {
        return profExperienceService.searchByIndustry(industry);
    }
}
