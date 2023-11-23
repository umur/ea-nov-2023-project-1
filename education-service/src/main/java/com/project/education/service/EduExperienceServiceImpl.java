package com.project.education.service;

import com.project.education.dto.post.PostFullEduExperienceDto;
import com.project.education.dto.get.GetFullEduExperienceDto;
import com.project.education.dto.get.GetFullCourseDto;
import com.project.business.model.*;
import com.project.education.repository.CourseRepository;
import com.project.education.repository.EduExperienceRepository;
import com.project.education.repository.UniversityRepository;
import com.project.education.model.Course;
import com.project.education.model.EduExperience;
import com.project.education.model.University;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EduExperienceServiceImpl implements EduExperienceService {
    private final EduExperienceRepository eduExperienceRepository;
    private final UniversityRepository universityRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public GetFullEduExperienceDto addEduExperience(Long alumniId, PostFullEduExperienceDto eduExperienceDto) {
        University university = universityRepository.findById(eduExperienceDto.getUniversityId())
                .orElseThrow(() -> new RuntimeException("University not found with id: " + eduExperienceDto.getUniversityId()));
//        Alumni alumni = alumniRepository.findById(alumniId)
//                .orElseThrow(() -> new RuntimeException("Alumni not found with id: " + eduExperienceDto.getUniversityId()));
        List<Course> courseList = new ArrayList<>();
        eduExperienceDto.getCourseIdList().forEach(courseId -> {
            Course course = courseRepository.findById(courseId).orElseThrow();
            if (course.getUniversity().getId() != university.getId()) {
                throw new NoSuchElementException("Course not found with id:" + courseId);
            }
            courseList.add(course);
        });
        EduExperience eduExperience = modelMapper.map(eduExperienceDto, EduExperience.class);
        eduExperience.setUniversity(university);
        eduExperience.setCourses(courseList);
//        eduExperience.setAlumni(alumni);
        eduExperienceRepository.save(eduExperience);
        return populateCoursesAndReturnDto(eduExperience);
    }

//    @Override
//    public GetFullEduExperienceDto getByAlumniId(Long alumniId) {
//        EduExperience eduExperience = eduExperienceRepository.findByAlumni_Id(alumniId).orElseThrow();
//        return populateCoursesAndReturnDto(eduExperience);
//    }

    @Transactional
    @Override
    public GetFullEduExperienceDto updateById(Long alumniId, PostFullEduExperienceDto eduExperienceDto) {
        University university = universityRepository.findById(eduExperienceDto.getUniversityId())
                .orElseThrow(() -> new RuntimeException("University not found with id: " + eduExperienceDto.getUniversityId()));
//        Alumni alumni = alumniRepository.findById(alumniId)
//                .orElseThrow(() -> new RuntimeException("Alumni not found with id: " + eduExperienceDto.getUniversityId()));
        List<Course> courseList = new ArrayList<>();
        eduExperienceDto.getCourseIdList().forEach(courseId -> {
            Course course = courseRepository.findById(courseId).orElseThrow();
            if (course.getUniversity().getId() != university.getId()) {
                throw new NoSuchElementException("Course not found with id:" + courseId);
            }
            courseList.add(course);
        });
//        EduExperience eduExperience = eduExperienceRepository.findByAlumni_Id(alumniId).orElseThrow();
//        eduExperience.setUniversity(university);
//        eduExperience.setCourses(courseList);
////        eduExperience.setAlumni(alumni);
//        eduExperience.setDegree(eduExperienceDto.getDegree());
//        eduExperience.setEndDate(eduExperienceDto.getEndDate());
//        eduExperience.setStartDate(eduExperienceDto.getStartDate());
//        eduExperienceRepository.save(eduExperience);
//        return populateCoursesAndReturnDto(eduExperience);
        return null;
    }

    @Transactional
    @Override
    public GetFullEduExperienceDto deleteById(Long alumniId) {
//        EduExperience eduExperience = eduExperienceRepository.findByAlumni_Id(alumniId).orElseThrow();
//        GetFullEduExperienceDto retEduExperience = populateCoursesAndReturnDto(eduExperience);
//        eduExperienceRepository.delete(eduExperience);
//        return retEduExperience;
        return null;

    }
    private GetFullEduExperienceDto populateCoursesAndReturnDto(EduExperience eduExperience){
        List<GetFullCourseDto> courseDtoList = new ArrayList<>();
        GetFullEduExperienceDto obj = modelMapper.map(eduExperience, GetFullEduExperienceDto.class);
        eduExperience.getCourses().forEach(course ->
                courseDtoList.add(modelMapper.map(course, GetFullCourseDto.class)));
        obj.setCourseList(courseDtoList);
        return obj;
    }
}
