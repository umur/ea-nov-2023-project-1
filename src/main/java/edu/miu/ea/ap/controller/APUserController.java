package edu.miu.ea.ap.controller;

import edu.miu.ea.ap.model.dto.request.APUserRequestDTO;
import edu.miu.ea.ap.model.dto.response.APUserResponseDTO;
import edu.miu.ea.ap.service.APUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class APUserController {

    @Autowired
    APUserService service;

    @PostMapping
    public ResponseEntity getAll() {
        try {
            List<APUserResponseDTO> userResponseDTOs = service.getAll();
            return ResponseEntity.ok(userResponseDTOs);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PostMapping(value = "/registration/user")
    public ResponseEntity registeUser(@RequestBody APUserRequestDTO userRequestDTO) {
        try {
            APUserResponseDTO userResponseDTO = service.saveUser(userRequestDTO);
            return ResponseEntity.ok(userResponseDTO);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PostMapping(value = "/registration/admin")
    public ResponseEntity registeAdmin(@RequestBody APUserRequestDTO userRequestDTO) {
        try {
            APUserResponseDTO userResponseDTO = service.saveAdmin(userRequestDTO);
            return ResponseEntity.ok(userResponseDTO);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody APUserRequestDTO userRequestDTO) {

        try {
            boolean result = service.changePassword(userRequestDTO);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
