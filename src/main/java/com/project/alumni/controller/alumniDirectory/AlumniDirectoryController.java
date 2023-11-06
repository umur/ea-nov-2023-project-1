package com.project.alumni.controller.alumniDirectory;

import com.project.alumni.dto.alumniDirectory.SearchAlumniDirectoryDto;
import com.project.alumni.dto.UserFullDetailsDto;
import com.project.alumni.service.alumniDirectory.AlumniDirectoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alumniDirectory")
public class AlumniDirectoryController {

    private final AlumniDirectoryService alumniDirectoryService;

    @GetMapping("/search")
    public List<UserFullDetailsDto> searchAlumniDirectory(@RequestBody SearchAlumniDirectoryDto searchAlumniDirectoryDto) {
        return alumniDirectoryService.alumniDirectorySearch(searchAlumniDirectoryDto);
    }


}
