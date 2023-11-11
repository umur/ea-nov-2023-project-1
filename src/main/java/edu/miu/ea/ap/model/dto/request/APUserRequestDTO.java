package edu.miu.ea.ap.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.miu.ea.ap.helper.configuration.DateConfigFinals;
import edu.miu.ea.ap.model.domain.APAddress;
import lombok.Data;

import java.util.Date;

@Data
public class APUserRequestDTO extends APRequestDTO {

    private String username;
    private String mobileNumber;
    private String email;
    private String language;

    private String nameFirst;
    private String nameLast;
    private APAddress location;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConfigFinals.DATE_FORMAT)
    private Date dateOfBirth;

    private String roleType;

}
