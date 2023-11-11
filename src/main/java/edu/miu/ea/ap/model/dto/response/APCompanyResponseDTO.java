package edu.miu.ea.ap.model.dto.response;

import edu.miu.ea.ap.model.dto.APAddressDTO;
import lombok.Data;

@Data
public class APCompanyResponseDTO extends APResponseDTO {

    private String companyName;
    private APAddressDTO location;

}
