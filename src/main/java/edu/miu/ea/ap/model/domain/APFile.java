package edu.miu.ea.ap.model.domain;

import edu.miu.ea.ap.model.domain.APBaseEntityWithIdAuto;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "AP_FILES")
public class APFile extends APBaseEntityWithIdAuto {

    private String name;
    private String type;
    private String path;

    public APFile() {
    }

    public APFile(String name, String type, String path) {
        this.name = name;
        this.type = type;
        this.path = path;
    }

}
