package edu.miu.ea.ap.service;

import edu.miu.ea.ap.model.dto.request.APSurveyRequestDTO;
import edu.miu.ea.ap.model.dto.response.APSurveyResponseDTO;

import java.util.List;

public interface APSurveyService {

    List<APSurveyResponseDTO> getAll();
    List<APSurveyResponseDTO> getAllUnretired();
    APSurveyResponseDTO getById(APSurveyRequestDTO requestDTO);
    APSurveyResponseDTO save(APSurveyRequestDTO requestDTO);
    void retire(APSurveyRequestDTO requestDTO);
    void unretire(APSurveyRequestDTO requestDTO);

}
