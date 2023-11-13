package edu.miu.ea.ap.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class APEventRequestDTO extends APRequestDTO {

    private String name;
    private String type;

    private APUserRequestDTO organizer;

    private List<APUserRequestDTO> attendees;

}
