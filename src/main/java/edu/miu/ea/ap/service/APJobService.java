package edu.miu.ea.ap.service;

import edu.miu.ea.ap.model.dto.request.APJobRequestDTO;
import edu.miu.ea.ap.model.dto.response.APJobResponseDTO;

import java.util.List;

public interface APJobService {

    List<APJobResponseDTO> getAll();
    List<APJobResponseDTO> getAllUnretired();
    APJobResponseDTO getById(APJobRequestDTO requestDTO);
    APJobResponseDTO save(APJobRequestDTO requestDTO);
    void retire(APJobRequestDTO requestDTO);
    void unretire(APJobRequestDTO requestDTO);

}
