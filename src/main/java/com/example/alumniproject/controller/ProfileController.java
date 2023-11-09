package com.example.alumniproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.alumniproject.entity.Profile;
import com.example.alumniproject.service.ProfileService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    private ProfileService profileService;

    @PostMapping("")
    public ResponseEntity<Profile> add(@RequestBody Profile profile) {
        Profile saved = profileService.save(profile);
        return new ResponseEntity<Profile>(saved, HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<Profile> getAll() {
        return profileService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> search(@PathVariable Long id) {
        Optional<Profile> searched = profileService.findById(id);
        if (searched.isPresent()) {
            return new ResponseEntity<Profile>(searched.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> edit(@PathVariable Long id, @RequestBody Profile profile) {
        Profile updated = profileService.update(id, profile);
        return new ResponseEntity<Profile>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        profileService.deleteById(id);
    }
}
