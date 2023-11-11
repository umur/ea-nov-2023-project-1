package edu.miu.ea.ap.model.dto.response;

import lombok.Data;

@Data
public class APMessageResponseDTO extends APResponseDTO {

    private String content;

    private APUserResponseDTO sender;

    private APMeetingResponseDTO meeting;

}
