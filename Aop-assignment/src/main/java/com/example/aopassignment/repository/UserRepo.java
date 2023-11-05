package com.example.aopassignment.repository;

import com.example.aopassignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findById(int id);

    User updateById(int id,User user);
    void deleteById(int id);

}
