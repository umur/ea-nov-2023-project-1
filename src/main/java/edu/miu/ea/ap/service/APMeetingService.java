package edu.miu.ea.ap.service;

import edu.miu.ea.ap.model.dto.request.APMeetingRequestDTO;
import edu.miu.ea.ap.model.dto.response.APMeetingResponseDTO;

import java.util.List;

public interface APMeetingService {

    List<APMeetingResponseDTO> getAll();
    List<APMeetingResponseDTO> getAllUnretired();
    APMeetingResponseDTO getById(APMeetingRequestDTO requestDTO);
    APMeetingResponseDTO save(APMeetingRequestDTO requestDTO);
    void retire(APMeetingRequestDTO requestDTO);
    void unretire(APMeetingRequestDTO requestDTO);

}
