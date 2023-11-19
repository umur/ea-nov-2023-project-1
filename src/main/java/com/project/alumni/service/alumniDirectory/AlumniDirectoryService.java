package com.project.alumni.service.alumniDirectory;

import com.project.alumni.dto.user.UserFullDetailsDto;
import com.project.alumni.dto.alumniDirectory.SearchAlumniDirectoryDto;

import java.util.List;


public interface AlumniDirectoryService {
    public List<UserFullDetailsDto> alumniDirectorySearch(SearchAlumniDirectoryDto searchAlumniDirectoryDto);

}
