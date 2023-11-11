package edu.miu.ea.ap.model.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class APEventResponseDTO extends APResponseDTO {

    private String name;
    private String type;

    private APUserResponseDTO organizer;

    private List<APUserResponseDTO> attendees;

}
