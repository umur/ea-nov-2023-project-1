package edu.miu.ea.ap.model.dto.request;

import edu.miu.ea.ap.model.dto.APDtoModel;
import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class APRequestDTO extends APDtoModel {

    private Long id;
    private boolean retired;

}
