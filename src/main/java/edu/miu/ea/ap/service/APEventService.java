package edu.miu.ea.ap.service;

import edu.miu.ea.ap.model.dto.request.APEventRequestDTO;
import edu.miu.ea.ap.model.dto.response.APEventResponseDTO;

import java.util.List;

public interface APEventService {

    List<APEventResponseDTO> getAll();
    List<APEventResponseDTO> getAllUnretired();
    APEventResponseDTO getById(APEventRequestDTO requestDTO);
    APEventResponseDTO save(APEventRequestDTO requestDTO);
    void retire(APEventRequestDTO requestDTO);
    void unretire(APEventRequestDTO requestDTO);

}
