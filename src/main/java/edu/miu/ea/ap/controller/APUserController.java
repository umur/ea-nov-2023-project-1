package edu.miu.ea.ap.controller;

import edu.miu.ea.ap.model.dto.request.APUserRequestDTO;
import edu.miu.ea.ap.model.dto.response.APUserResponseDTO;
import edu.miu.ea.ap.service.HCUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class APUserController {

    @Autowired
    HCUserService userService;

    @PostMapping
    public ResponseEntity getAll() {
        try {
            List<APUserResponseDTO> userResponseDTOs = userService.getAll();
            return ResponseEntity.ok(userResponseDTOs);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PostMapping(value = "/registration/user")
    public ResponseEntity registeUser(@RequestBody APUserRequestDTO userRequestDTO) {
        try {
            APUserResponseDTO userResponseDTO = userService.saveUser(userRequestDTO);
            return ResponseEntity.ok(userResponseDTO);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PostMapping(value = "/registration/admin")
    public ResponseEntity registeAdmin(@RequestBody APUserRequestDTO userRequestDTO) {
        try {
            APUserResponseDTO userResponseDTO = userService.saveAdmin(userRequestDTO);
            return ResponseEntity.ok(userResponseDTO);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody APUserRequestDTO userRequestDTO) {

        try {
            boolean result = userService.changePassword(userRequestDTO);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
