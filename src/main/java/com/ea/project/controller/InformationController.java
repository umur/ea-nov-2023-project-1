package com.ea.project.controller;

import com.ea.project.dto.InformationDto;
import com.ea.project.service.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/information")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InformationController {
    @Autowired
    private final InformationService informationService;

    @GetMapping
    public List<InformationDto> findAll(){
        return informationService.findAll();
    }

    @GetMapping("/{id}")
    public InformationDto findById(@PathVariable int id){
        return informationService.findById(id);
    }

    @PostMapping
    public void creat(@RequestBody InformationDto information){
        informationService.create(information);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        informationService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody InformationDto information){informationService.update(information);
    }
}
