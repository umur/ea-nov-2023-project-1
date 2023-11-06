package com.project.alumni.service.alumniDirectory;

import com.project.alumni.dto.UserFullDetailsDto;
import com.project.alumni.dto.alumniDirectory.SearchAlumniDirectoryDto;
import com.project.alumni.entity.Course;
import com.project.alumni.entity.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public interface AlumniDirectoryService {
    public List<UserFullDetailsDto> alumniDirectorySearch(SearchAlumniDirectoryDto searchAlumniDirectoryDto);

}
