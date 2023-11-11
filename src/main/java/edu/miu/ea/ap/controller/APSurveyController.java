package edu.miu.ea.ap.controller;

import edu.miu.ea.ap.model.dto.request.APSurveyRequestDTO;
import edu.miu.ea.ap.model.dto.request.APUserRequestDTO;
import edu.miu.ea.ap.model.dto.response.APSurveyResponseDTO;
import edu.miu.ea.ap.model.dto.response.APUserResponseDTO;
import edu.miu.ea.ap.service.APSurveyService;
import edu.miu.ea.ap.service.APUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveys")
public class APSurveyController {

    @Autowired
    APSurveyService service;

    @PostMapping
    public ResponseEntity getAll() {
        try {
            List<APSurveyResponseDTO> responseDTOs = service.getAll();
            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/valid")
    public ResponseEntity getAllValid() {
        try {
            List<APSurveyResponseDTO> responseDTOs = service.getAllUnretired();
            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity save(@RequestBody APSurveyRequestDTO requestDTO) {
        try {
            APSurveyResponseDTO responseDTO = service.save(requestDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @DeleteMapping(value = "/retire")
    public ResponseEntity retire(@RequestBody APSurveyRequestDTO requestDTO) {
        try {
            service.retire(requestDTO);
            return ResponseEntity.ok("{}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/unretire")
    public ResponseEntity unretire(@RequestBody APSurveyRequestDTO requestDTO) {
        try {
            service.unretire(requestDTO);
            return ResponseEntity.ok("{}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
