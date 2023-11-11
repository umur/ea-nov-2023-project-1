package edu.miu.ea.ap.model.domain;

import lombok.Data;


import javax.persistence.*;

@MappedSuperclass
@Data
public class APBaseEntityWithIdAuto extends APBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
