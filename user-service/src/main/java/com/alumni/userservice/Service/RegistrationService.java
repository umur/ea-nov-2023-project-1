package com.alumni.userservice.Service;


import com.alumni.userservice.Dto.*;
import com.alumni.userservice.Entity.*;

import java.util.List;

public interface RegistrationService {
    UserDTO register(RegistrationDTO registrationDTO);

    User userDTOToEntity(UserDTO userDTO);

    Profile profileDTOToEntity(ProfileDTO profileDTO);

    Education educationDTOToEntity(EducationDTO educationDTO);

    Course courseDTOToEntity(CourseDTO courseDTO);

//    Job jobDTOToEntity(JobDTO jobDTO);

    Achievement achievementDTOToEntity(AchievementDTO achievementDTO);

    List<Education> educationDTOListToEntity(List<EducationDTO> educationDTOList);

    List<Course> courseDTOListToEntity(List<CourseDTO> courseDTOList);

//    List<Job> jobDTOListToEntity(List<JobDTO> jobDTOList);

    List<Achievement> achievementDTOListToEntity(List<AchievementDTO> achievementDTOList);

    UserDTO entityToUserDTO(User user);
}
