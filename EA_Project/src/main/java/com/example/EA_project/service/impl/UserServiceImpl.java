package com.example.EA_project.service.impl;

import com.example.EA_project.entity.User;
import com.example.EA_project.repository.UserRepo;
import com.example.EA_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepo usRepo;



    public User addUser(User u) {
        return usRepo.save(u);
    }

    public List<User> getUsersWhereNameHas(String name) {
        return usRepo.findByNameContainingAndIsDeleted(name, false);
    }

    public List<User> getStudentByMajor(String major) {
        return usRepo.findByMajorAndIsDeleted(major, false);
    }

    public List<User> getAllUsers() {
        return usRepo.findAll();
    }

    public void deleteUser(Long id) {
        usRepo.updateUserByIdIs(id);
    }
    public Optional<User> getUser(Long id) {
        return usRepo.findById(id);
    }

    public List<User> getUsersWhereIdHas(Long id) {
        return usRepo.findByIdContainingAndIsDeleted(id, false);
    }




}
