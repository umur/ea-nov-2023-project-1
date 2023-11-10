package com.miu.alumnimanagementportal.repositories;

import com.miu.alumnimanagementportal.dtos.SearchDto;
import com.miu.alumnimanagementportal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("select u from User u inner join u.profile p inner join Address a inner join EducationDetails ed inner join WorkExperience we " +
            "where ed.passingYear = :graduationYear and ed.course = :course and a.city = :city and a.state = :state and we.companyName = :industry")
    List<User> findUsersByFileter(String graduationYear, String course, String location, String industry);
}