package edu.miu.ea.ap.controller;

import edu.miu.ea.ap.model.dto.request.APMeetingRequestDTO;
import edu.miu.ea.ap.model.dto.response.APMeetingResponseDTO;
import edu.miu.ea.ap.service.APMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetings")
public class APMeetingController {

    @Autowired
    APMeetingService service;

    @PostMapping
    public ResponseEntity getAll() {
        try {
            List<APMeetingResponseDTO> responseDTOs = service.getAll();
            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/valid")
    public ResponseEntity getAllValid() {
        try {
            List<APMeetingResponseDTO> responseDTOs = service.getAllUnretired();
            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity save(@RequestBody APMeetingRequestDTO requestDTO) {
        try {
            APMeetingResponseDTO responseDTO = service.save(requestDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @DeleteMapping(value = "/retire")
    public ResponseEntity retire(@RequestBody APMeetingRequestDTO requestDTO) {
        try {
            service.retire(requestDTO);
            return ResponseEntity.ok("{}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/unretire")
    public ResponseEntity unretire(@RequestBody APMeetingRequestDTO requestDTO) {
        try {
            service.unretire(requestDTO);
            return ResponseEntity.ok("{}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
