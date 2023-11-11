package edu.miu.ea.ap.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.miu.ea.ap.model.domain.APAddress;
import lombok.Data;
import edu.miu.ea.ap.helper.configuration.DateConfigFinals;

import java.util.Date;

@Data
public class APUserResponseDTO extends APResponseDTO {

    private String username;
    private String mobileNumber;
    private String email;
    private String language;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConfigFinals.DATETIME_FORMAT)
    private Date lastSignIn;

    private String nameFirst;
    private String nameLast;
    private APAddress location;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConfigFinals.DATE_FORMAT)
    private Date dateOfBirth;

    private String roleType;

}
