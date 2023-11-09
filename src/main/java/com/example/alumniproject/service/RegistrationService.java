package com.example.alumniproject.service;

import java.util.List;

import com.example.alumniproject.dto.AchievementDTO;
import com.example.alumniproject.dto.CourseDTO;
import com.example.alumniproject.dto.EducationDTO;
import com.example.alumniproject.dto.JobDTO;
import com.example.alumniproject.dto.ProfileDTO;
import com.example.alumniproject.dto.RegistrationDTO;
import com.example.alumniproject.dto.UserDTO;
import com.example.alumniproject.entity.Achievement;
import com.example.alumniproject.entity.Course;
import com.example.alumniproject.entity.Education;
import com.example.alumniproject.entity.Job;
import com.example.alumniproject.entity.Profile;
import com.example.alumniproject.entity.User;

public interface RegistrationService {
    UserDTO register(RegistrationDTO registrationDTO);

    User userDTOToEntity(UserDTO userDTO);

    Profile profileDTOToEntity(ProfileDTO profileDTO);

    Education educationDTOToEntity(EducationDTO educationDTO);

    Course courseDTOToEntity(CourseDTO courseDTO);

    Job jobDTOToEntity(JobDTO jobDTO);

    Achievement achievementDTOToEntity(AchievementDTO achievementDTO);

    List<Education> educationDTOListToEntity(List<EducationDTO> educationDTOList);

    List<Course> courseDTOListToEntity(List<CourseDTO> courseDTOList);

    List<Job> jobDTOListToEntity(List<JobDTO> jobDTOList);

    List<Achievement> achievementDTOListToEntity(List<AchievementDTO> achievementDTOList);

    UserDTO entityToUserDTO(User user);
}
