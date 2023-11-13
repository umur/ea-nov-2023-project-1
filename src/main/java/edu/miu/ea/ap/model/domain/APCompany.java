package edu.miu.ea.ap.model.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class APCompany {

    private String companyName;
    private APAddress location;

}
