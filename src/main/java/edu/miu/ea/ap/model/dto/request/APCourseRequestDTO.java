package edu.miu.ea.ap.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class APCourseRequestDTO extends APRequestDTO {

    private String name;
    private String code;
    private String location;
    private Integer credit;

    private List<APUserRequestDTO> students;

}
