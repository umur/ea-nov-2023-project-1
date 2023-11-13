package edu.miu.ea.ap.service;

import edu.miu.ea.ap.model.dto.request.APNewsItemRequestDTO;
import edu.miu.ea.ap.model.dto.response.APNewsItemResponseDTO;

import java.util.List;

public interface APNewsService {

    List<APNewsItemResponseDTO> getAll();
    List<APNewsItemResponseDTO> getAllUnretired();
    APNewsItemResponseDTO getById(APNewsItemRequestDTO requestDTO);
    APNewsItemResponseDTO save(APNewsItemRequestDTO requestDTO);
    void retire(APNewsItemRequestDTO requestDTO);
    void unretire(APNewsItemRequestDTO requestDTO);

}
