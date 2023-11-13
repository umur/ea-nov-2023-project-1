package edu.miu.ea.ap.service;

import edu.miu.ea.ap.model.dto.request.APLookupRequestDTO;
import edu.miu.ea.ap.model.dto.response.APLookupResponseDTO;

import java.util.List;

public interface APLookupService {

    List<APLookupResponseDTO> getAll();
    List<APLookupResponseDTO> getAllByType(String type, boolean validOnly);
    APLookupResponseDTO save(APLookupRequestDTO lookupRequestDTO);
    List<APLookupResponseDTO> save(List<APLookupRequestDTO> lookupRequestDTOs);

}
