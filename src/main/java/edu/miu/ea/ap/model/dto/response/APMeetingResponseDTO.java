package edu.miu.ea.ap.model.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class APMeetingResponseDTO extends APResponseDTO {

    private String title;

    private APUserResponseDTO organizer;

    private List<APUserResponseDTO> attendees;

}
