package com.miu.alumnimanagementportal.repositories;

import com.miu.alumnimanagementportal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}