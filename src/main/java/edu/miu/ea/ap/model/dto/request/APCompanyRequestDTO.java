package edu.miu.ea.ap.model.dto.request;

import edu.miu.ea.ap.model.dto.APAddressDTO;
import lombok.Data;

@Data
public class APCompanyRequestDTO extends APRequestDTO {

    private String companyName;
    private APAddressDTO location;

}
