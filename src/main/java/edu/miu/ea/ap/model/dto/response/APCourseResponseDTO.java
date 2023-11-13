package edu.miu.ea.ap.model.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class APCourseResponseDTO extends APResponseDTO {

    private String name;
    private String code;
    private String location;
    private Integer credit;

    private List<APUserResponseDTO> students;

}
