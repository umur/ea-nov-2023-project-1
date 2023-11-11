package edu.miu.ea.ap.model.domain;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class APBaseEntityWithIdManual extends APBaseEntity {

    @Id
    private Long id;

}
