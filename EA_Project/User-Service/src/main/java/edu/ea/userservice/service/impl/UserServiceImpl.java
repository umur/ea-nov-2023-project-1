package edu.ea.userservice.service.impl;


import edu.ea.userservice.dto.AuthRequest;
import edu.ea.userservice.dto.UserDto;
import edu.ea.userservice.model.User;
import edu.ea.userservice.repository.UserRepo;
import edu.ea.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepo usRepo;

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    @Override
    public  UserDto getUser(Long id) throws Exception {
        Optional<User> byId = usRepo.findById(id);
        if(!byId.isPresent())
        throw  new Exception("User not found");
        return   modelMapper.map(byId.get(),UserDto.class);
    }

    @Override
    public UserDto getEmail(String email ) throws Exception {
        Optional<User> byId = usRepo.findByEmail(email);
        if(!byId.isPresent())
            throw  new Exception("User not found");
        return   modelMapper.map(byId.get(),UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return usRepo.findAll().stream().map(u -> modelMapper.map(u,UserDto.class)).collect(Collectors.toList()) ;
    }

    @Override
    public UserDto addUser(UserDto u) {
        u.setPassword( passwordEncoder.encode(u.getPassword()));
        u.setActive(true);
        User saved = usRepo.save(modelMapper.map(u,User.class));
        return modelMapper.map(saved,UserDto.class);
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        Optional<User> byId = usRepo.findById(id);
        if(!byId.isPresent())
            throw  new Exception("User not found");
        User user = byId.get();
         user.setDeleted(true);
         usRepo.save(user);
    }

    @Override
    public void activateUser(Long id) throws Exception {
        Optional<User> byId = usRepo.findById(id);
        if(!byId.isPresent())
            throw  new Exception("User not found");
        User user = byId.get();
        user.setActive(true);
        usRepo.save(user);
    }

    @Override
    public void deactivateUser(Long id) throws Exception {
        Optional<User> byId = usRepo.findById(id);
        if(!byId.isPresent())
            throw  new Exception("User not found");
        User user = byId.get();
        user.setActive(false);
        usRepo.save(user);
    }

    @Override
    public void restPassword(Long id, AuthRequest authRequest) throws Exception {
        Optional<User> byId = usRepo.findById(id);
        if(!byId.isPresent())
            throw  new Exception("User not found");
        User user = byId.get();
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        usRepo.save(user);
    }
}
