package edu.miu.ea.ap.service;

import edu.miu.ea.ap.helper.Enumerations;
import edu.miu.ea.ap.model.domain.APUser;
import edu.miu.ea.ap.model.dto.request.APUserRequestDTO;
import edu.miu.ea.ap.model.dto.response.APUserResponseDTO;

import java.util.List;

public interface APUserService {

    APUser getLoggedinUser();
    APUser getUserByUsername(String username);
    List<APUserResponseDTO> getAll();
    APUserResponseDTO save(APUserRequestDTO userRequestDTO);
    APUserResponseDTO saveUser(APUserRequestDTO userRequestDTO);
    APUserResponseDTO saveAdmin(APUserRequestDTO userRequestDTO);
    void updateLastSignIn(APUser user);
    boolean changePassword(APUserRequestDTO userRequestDTO);

}
