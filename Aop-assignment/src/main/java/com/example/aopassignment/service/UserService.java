package com.example.aopassignment.service;

import com.example.aopassignment.model.User;

public interface UserService {
    User findById(int id);
    User saveUser(User user);
    User updateUser(int id,User user);
    void deleteUser(int id);

}
