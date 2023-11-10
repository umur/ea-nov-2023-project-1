package com.example.alumniproject.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.example.alumniproject.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.alumniproject.dto.AchievementDTO;
import com.example.alumniproject.dto.CourseDTO;
import com.example.alumniproject.dto.EducationDTO;
import com.example.alumniproject.dto.JobDTO;
import com.example.alumniproject.dto.ProfileDTO;
import com.example.alumniproject.dto.RegistrationDTO;
import com.example.alumniproject.dto.UserDTO;
import com.example.alumniproject.repository.UserRepo;
import com.example.alumniproject.service.RegistrationService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepo userRepo;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDTO register(RegistrationDTO registrationDTO) {
        Profile profile = profileDTOToEntity(registrationDTO.getProfile());

        User user = userDTOToEntity(registrationDTO.getUser());

        user.setProfile(profile);

        User temp = userRepo.save(user);
        return entityToUserDTO(temp);
    }

    @Override
    public User userDTOToEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setAccountLocked(false);
        return user;
    }

    @Override
    public Profile profileDTOToEntity(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setPhoneNumber(profileDTO.getPhoneNumber());
        profile.setMajor(profileDTO.getMajor());

        profile.setCountry(profileDTO.getCountry());
        profile.setState(profileDTO.getState());
        profile.setStreet(profileDTO.getStreet());
        profile.setZip(profileDTO.getZip());
        profile.setCity(profileDTO.getCity());
        profile.setProfileImage(profileDTO.getProfileImage());

        if (profileDTO.getEducation() == null || profileDTO.getEducation().isEmpty()) {
            throw new IllegalArgumentException("Education is required.");
        }

        profile.setEducation(educationDTOListToEntity(profileDTO.getEducation()));
        profile.setJobs(jobDTOListToEntity(profileDTO.getJobs()));
        profile.setAchievements(achievementDTOListToEntity(profileDTO.getAchievements()));

        return profile;
    }

    @Override
    public List<Achievement> achievementDTOListToEntity(List<AchievementDTO> achievementDTOList) {
        return achievementDTOList.stream()
                .map(this::achievementDTOToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Achievement achievementDTOToEntity(AchievementDTO achievementDTO) {
        Achievement achievement = new Achievement();
        achievement.setTitle(achievementDTO.getTitle());
        achievement.setDescription(achievementDTO.getDescription());
        return achievement;
    }

    @Override
    public List<Job> jobDTOListToEntity(List<JobDTO> jobDTOList) {
        return jobDTOList.stream()
                .map(this::jobDTOToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Job jobDTOToEntity(JobDTO jobDTO) {
        Job job = new Job();
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setOrganization(jobDTO.getOrganization());
        return job;
    }


    @Override
    public List<Education> educationDTOListToEntity(List<EducationDTO> educationDTOList) {
        return educationDTOList.stream()
                .map(this::educationDTOToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Education educationDTOToEntity(EducationDTO educationDTO) {
        Education education = new Education();
        education.setDegree(educationDTO.getDegree());
        education.setUniversity(educationDTO.getUniversity());
        education.setGraduationYear(educationDTO.getGraduationYear());
        List<Course> courses = courseDTOListToEntity(educationDTO.getCourses());
        education.setCourses(courses);
        return education;
    }

    @Override
    public List<Course> courseDTOListToEntity(List<CourseDTO> courseDTOList) {
        return courseDTOList.stream()
                .map(this::courseDTOToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Course courseDTOToEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setCode(courseDTO.getCode());
        course.setGpa(courseDTO.getGpa());
        return course;
    }

    @Override
    public UserDTO entityToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
