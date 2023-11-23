package edu.ea.userservice.service;


import edu.ea.userservice.dto.AuthRequest;
import edu.ea.userservice.dto.UserDto;
import edu.ea.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
      UserDto getUser(Long id) throws Exception;
    UserDto getEmail(String  email) throws Exception;
     List<UserDto> getAllUsers() ;
    UserDto addUser(UserDto u) ;
      void deleteUser(Long id) throws Exception;
      void activateUser(Long id) throws Exception;
      void deactivateUser(Long id) throws Exception;

      void restPassword(Long id, AuthRequest authRequest) throws Exception;




}
