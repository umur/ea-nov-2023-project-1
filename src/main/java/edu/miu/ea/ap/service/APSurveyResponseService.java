package edu.miu.ea.ap.service;

import edu.miu.ea.ap.model.dto.request.APSurveyResponseRequestDTO;
import edu.miu.ea.ap.model.dto.response.APSurveyResponseResponseDTO;

import java.util.List;

public interface APSurveyResponseService {

    List<APSurveyResponseResponseDTO> getAll();
    List<APSurveyResponseResponseDTO> getAllUnretired();
    APSurveyResponseResponseDTO getById(APSurveyResponseRequestDTO requestDTO);
    APSurveyResponseResponseDTO save(APSurveyResponseRequestDTO requestDTO);
    void retire(APSurveyResponseRequestDTO requestDTO);
    void unretire(APSurveyResponseRequestDTO requestDTO);

}
