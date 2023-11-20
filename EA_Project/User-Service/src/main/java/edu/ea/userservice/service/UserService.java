package edu.ea.userservice.service;


import edu.ea.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {


    public User addUser(User u) ;



    public List<User> getUsersWhereNameHas(String name) ;



    public void deleteUser(Long id);
    public List<User> getAllUsers() ;

    public Optional<User> getUser(Long id) ;

    public List<User> getUsersWhereIdHas(Long id) ;



}
