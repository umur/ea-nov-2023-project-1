package edu.miu.ea.ap.controller;

import edu.miu.ea.ap.model.dto.request.APEventRequestDTO;
import edu.miu.ea.ap.model.dto.response.APEventResponseDTO;
import edu.miu.ea.ap.service.APEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class APEventController {

    @Autowired
    APEventService service;

    @PostMapping
    public ResponseEntity getAll() {
        try {
            List<APEventResponseDTO> responseDTOs = service.getAll();
            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/valid")
    public ResponseEntity getAllValid() {
        try {
            List<APEventResponseDTO> responseDTOs = service.getAllUnretired();
            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity save(@RequestBody APEventRequestDTO requestDTO) {
        try {
            APEventResponseDTO responseDTO = service.save(requestDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @DeleteMapping(value = "/retire")
    public ResponseEntity retire(@RequestBody APEventRequestDTO requestDTO) {
        try {
            service.retire(requestDTO);
            return ResponseEntity.ok("{}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/unretire")
    public ResponseEntity unretire(@RequestBody APEventRequestDTO requestDTO) {
        try {
            service.unretire(requestDTO);
            return ResponseEntity.ok("{}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
