package edu.miu.ea.ap.model.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "AP_SETTINGS")
@Data
public class APSetting extends APBaseEntityWithIdManual {

    private String key;
    private String value;

}
