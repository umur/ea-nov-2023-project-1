package com.project.education.service;

import com.project.education.dto.get.GetFullUniversityDto;
import com.project.education.dto.post.PostFullUniversityDto;
import com.project.education.model.University;
import com.project.education.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<GetFullUniversityDto> getAllUniversities() {
        List<University> universities = universityRepository.findAll();
        return universities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GetFullUniversityDto getUniversityById(long id) {
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found with id: " + id));
        return convertToDto(university);
    }

    @Override
    public GetFullUniversityDto saveUniversity(PostFullUniversityDto postFullUniversityDto) {
        University university = convertToEntity(postFullUniversityDto);
        University savedUniversity = universityRepository.save(university);
        return convertToDto(savedUniversity);
    }

    @Override
    public GetFullUniversityDto updateUniversity(long id, PostFullUniversityDto postFullUniversityDto) {
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found with id: " + id));
        university.setId(id);
        University updatedUniversity = universityRepository.save(university);
        return convertToDto(updatedUniversity);
    }

    @Override
    public void deleteUniversity(long id) {
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found with id: " + id));
        universityRepository.delete(university);
    }


    private GetFullUniversityDto convertToDto(University university) {
        return modelMapper.map(university, GetFullUniversityDto.class);
    }

    private University convertToEntity(PostFullUniversityDto postFullUniversityDto) {
        return modelMapper.map(postFullUniversityDto, University.class);
    }
}
