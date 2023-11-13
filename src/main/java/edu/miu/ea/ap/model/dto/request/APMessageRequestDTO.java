package edu.miu.ea.ap.model.dto.request;

import lombok.Data;

@Data
public class APMessageRequestDTO extends APRequestDTO {

    private String content;

    private APUserRequestDTO sender;

    private APMeetingRequestDTO meeting;

}
