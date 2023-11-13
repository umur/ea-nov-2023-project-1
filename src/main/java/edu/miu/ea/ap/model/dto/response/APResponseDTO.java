package edu.miu.ea.ap.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.miu.ea.ap.model.dto.APDtoModel;
import lombok.Data;
import edu.miu.ea.ap.helper.configuration.DateConfigFinals;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class APResponseDTO extends APDtoModel {

    private Long id;
    private boolean retired;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConfigFinals.DATETIME_FORMAT)
    private Date retiredAt;
    private Long retiredBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConfigFinals.DATETIME_FORMAT)
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConfigFinals.DATETIME_FORMAT)
    private Date modifiedAt;
    private APUserResponseDTO creatorUser;

}
