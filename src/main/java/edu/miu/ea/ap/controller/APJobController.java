package edu.miu.ea.ap.controller;

import edu.miu.ea.ap.model.dto.request.APJobRequestDTO;
import edu.miu.ea.ap.model.dto.response.APJobResponseDTO;
import edu.miu.ea.ap.service.APJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class APJobController {


    @Autowired
    APJobService service;

    @PostMapping
    public ResponseEntity getAll() {
        try {
            List<APJobResponseDTO> responseDTOs = service.getAll();
            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/valid")
    public ResponseEntity getAllValid() {
        try {
            List<APJobResponseDTO> responseDTOs = service.getAllUnretired();
            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity save(@RequestBody APJobRequestDTO requestDTO) {
        try {
            APJobResponseDTO responseDTO = service.save(requestDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @DeleteMapping(value = "/retire")
    public ResponseEntity retire(@RequestBody APJobRequestDTO requestDTO) {
        try {
            service.retire(requestDTO);
            return ResponseEntity.ok("{}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/unretire")
    public ResponseEntity unretire(@RequestBody APJobRequestDTO requestDTO) {
        try {
            service.unretire(requestDTO);
            return ResponseEntity.ok("{}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
