package com.example.aopassignment.service.impl;

import com.example.aopassignment.model.User;
import com.example.aopassignment.repository.UserRepo;
import com.example.aopassignment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Override
    public User findById(int id) {
        return userRepo.findById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(int id, User user) {
        return userRepo.updateById(id,user);
    }

    @Override
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }
}