package com.project.education.service;

import com.project.education.dto.post.PostFullEduExperienceDto;
import com.project.education.dto.get.GetFullEduExperienceDto;
import jakarta.transaction.Transactional;

public interface EduExperienceService {

//    GetFullEduExperienceDto getByAlumniId(Long alumniId);


    @Transactional
    GetFullEduExperienceDto updateById(Long alumniId, PostFullEduExperienceDto eduExperienceDto);

    @Transactional
    GetFullEduExperienceDto deleteById(Long alumniId);

    GetFullEduExperienceDto addEduExperience(Long alumniId, PostFullEduExperienceDto eduExperience);
}
