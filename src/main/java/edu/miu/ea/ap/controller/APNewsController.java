package edu.miu.ea.ap.controller;

import edu.miu.ea.ap.model.dto.request.APNewsItemRequestDTO;
import edu.miu.ea.ap.model.dto.response.APNewsItemResponseDTO;
import edu.miu.ea.ap.service.APNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class APNewsController {

    @Autowired
    APNewsService service;

    @PostMapping
    public ResponseEntity getAll() {
        try {
            List<APNewsItemResponseDTO> responseDTOs = service.getAll();
            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/valid")
    public ResponseEntity getAllValid() {
        try {
            List<APNewsItemResponseDTO> responseDTOs = service.getAllUnretired();
            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity save(@RequestBody APNewsItemRequestDTO requestDTO) {
        try {
            APNewsItemResponseDTO responseDTO = service.save(requestDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @DeleteMapping(value = "/retire")
    public ResponseEntity retire(@RequestBody APNewsItemRequestDTO requestDTO) {
        try {
            service.retire(requestDTO);
            return ResponseEntity.ok("{}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/unretire")
    public ResponseEntity unretire(@RequestBody APNewsItemRequestDTO requestDTO) {
        try {
            service.unretire(requestDTO);
            return ResponseEntity.ok("{}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
