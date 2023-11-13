package edu.miu.ea.ap.service.impl;

import edu.miu.ea.ap.helper.Enumerations;
import edu.miu.ea.ap.model.domain.APUser;
import edu.miu.ea.ap.model.dto.request.APUserRequestDTO;
import edu.miu.ea.ap.model.dto.response.APUserResponseDTO;
import edu.miu.ea.ap.service.APUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import edu.miu.ea.ap.repository.APUserRepository;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.*;

@Service
public class APUserServiceImpl implements APUserService {

    @Autowired
    APUserRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public APUser getLoggedinUser() {
        APUser loggedinUser = null;

        try {
            APUserResponseDTO userResponseDTO = (APUserResponseDTO) SecurityContextHolder.getContext().getAuthentication().getDetails();
            Optional<APUser> userInDD = repository.findById(userResponseDTO.getId());
            loggedinUser = userInDD.get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return loggedinUser;
    }

    @Override
    public APUser getUserByUsername(String username) {
        return repository.findByUsernameIgnoreCaseAndRetiredFalse(username);
    }

    @Override
    public List<APUserResponseDTO> getAll() {
        List<APUser> users = repository.findAllByOrderById();
        Type listOfDTOsType = new TypeToken<List<APUserResponseDTO>>() {}.getType();
        List<APUserResponseDTO> userResponseDTOs = modelMapper.map(users, listOfDTOsType);
        return userResponseDTOs;
    }

    @Override
    public APUserResponseDTO saveUser(APUserRequestDTO userRequestDTO) {
        userRequestDTO.setRoleType(Enumerations.RoleType.USER.name());
        return this.save(userRequestDTO);
    }

    @Override
    public APUserResponseDTO saveAdmin(APUserRequestDTO userRequestDTO) {
        userRequestDTO.setRoleType(Enumerations.RoleType.ADMIN.name());
        return this.save(userRequestDTO);
    }

    @Override
    public APUserResponseDTO save(APUserRequestDTO userRequestDTO) {
        if (userRequestDTO.getRoleType().isEmpty()) {
            userRequestDTO.setRoleType(Enumerations.RoleType.USER.name());
        }

        APUser user = modelMapper.map(userRequestDTO, APUser.class);

//        if (user.getId() == null) {
//            throw new RuntimeException("user id should be sent");
//        }

        try {
            Optional<APUser> oldUser = repository.findById(user.getId());
            APUser oldUserValue = oldUser.get();
            user.setCreatedAt(oldUserValue.getCreatedAt());
        } catch (Exception ex) {
        }

        user = repository.save(user);

        Optional<APUser> updatedUser;
        try {
            updatedUser = repository.findById(user.getId());
        } catch (Exception ex) {
            throw new RuntimeException("could not extract ResultSet - custom");
        }

        user = updatedUser.get();
        APUserResponseDTO userResponseDTO = modelMapper.map(user, APUserResponseDTO.class);

        return userResponseDTO;
    }

    @Override
    public void updateLastSignIn(APUser user) {
        user.setLastSignIn(new Timestamp(System.currentTimeMillis()));
        repository.save(user);
    }

    @Override
    public boolean changePassword(APUserRequestDTO userRequestDTO) {
        return true;
    }

}
