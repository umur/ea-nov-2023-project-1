package edu.miu.ea.ap.model.domain;

import edu.miu.ea.ap.helper.Enumerations;
import edu.miu.ea.ap.model.domain.APBaseEntityWithIdAuto;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "AP_PRIVILEGES")
public class APPrivilege extends APBaseEntityWithIdAuto {

    @Enumerated(EnumType.STRING)
    private Enumerations.RoleType name;

}
