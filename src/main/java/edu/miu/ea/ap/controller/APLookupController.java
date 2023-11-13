package edu.miu.ea.ap.controller;

import edu.miu.ea.ap.model.dto.request.APLookupRequestDTO;
import edu.miu.ea.ap.model.dto.response.APLookupResponseDTO;
import edu.miu.ea.ap.service.APLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lookups")
public class APLookupController {

    @Autowired
    APLookupService lookupService;

    @PostMapping
    public ResponseEntity getAll() {
        try {
            List<APLookupResponseDTO> lookupResponseDTOs = lookupService.getAll();
            return ResponseEntity.ok(lookupResponseDTOs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/allByType")
    public ResponseEntity getAllByType(@RequestBody APLookupRequestDTO lookupRequestDTO) {
        try {
            List<APLookupResponseDTO> lookupResponseDTOs = lookupService.getAllByType(lookupRequestDTO.getType(), false);
            return ResponseEntity.ok(lookupResponseDTOs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/allByTypeValid")
    public ResponseEntity getAllByTypeValid(@RequestBody APLookupRequestDTO lookupRequestDTO) {
        try {
            List<APLookupResponseDTO> lookupResponseDTOs = lookupService.getAllByType(lookupRequestDTO.getType(), true);
            return ResponseEntity.ok(lookupResponseDTOs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity save(@RequestBody APLookupRequestDTO lookupRequestDTO) {
        try {
            APLookupResponseDTO lookupResponseDTO = lookupService.save(lookupRequestDTO);
            return ResponseEntity.ok(lookupResponseDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/saveMany")
    public ResponseEntity save(@RequestBody List<APLookupRequestDTO> lookupRequestDTOs) {
        try {
            List<APLookupResponseDTO> lookupResponseDTOs = lookupService.save(lookupRequestDTOs);
            return ResponseEntity.ok(lookupResponseDTOs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
