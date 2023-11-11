package edu.miu.ea.ap.controller;

import edu.miu.ea.ap.model.dto.request.APSettingRequestDTO;
import edu.miu.ea.ap.model.dto.response.APSettingResponseDTO;
import edu.miu.ea.ap.service.HCSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/settings")
public class APSettingController {

    @Autowired
    HCSettingService settingService;

    @PostMapping
    public ResponseEntity getAll() {
        try {
            List<APSettingResponseDTO> settingResponseDTOs = settingService.getAll();
            return ResponseEntity.ok(settingResponseDTOs);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @PostMapping(value = "/get")
    public ResponseEntity get(@RequestBody APSettingRequestDTO settingRequestDTO) {
        try {
            APSettingResponseDTO settingResponseDTO = settingService.getSetting(settingRequestDTO);
            return ResponseEntity.ok(settingResponseDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity save(@RequestBody APSettingRequestDTO settingRequestDTO) {
        try {
            APSettingResponseDTO settingResponseDTO = settingService.saveSetting(settingRequestDTO);
            return ResponseEntity.ok(settingResponseDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
