package edu.ea.userservice.service.impl;


import edu.ea.userservice.model.User;
import edu.ea.userservice.repository.UserRepo;
import edu.ea.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepo usRepo;

    private final PasswordEncoder passwordEncoder;

    public User addUser(User u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return usRepo.save(u);
    }

    public List<User> getUsersWhereNameHas(String name) {
        return usRepo.findByNameContainingAndIsDeleted(name, false);
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
