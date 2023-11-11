package edu.miu.ea.ap.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class APMeetingRequestDTO extends APRequestDTO {

    private String title;

    private APUserRequestDTO organizer;

    private List<APUserRequestDTO> attendees;

}
