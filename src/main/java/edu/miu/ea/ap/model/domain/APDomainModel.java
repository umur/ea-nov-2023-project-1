package edu.miu.ea.ap.model.domain;

import lombok.Data;
import edu.miu.ea.ap.model.APModel;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class APDomainModel extends APModel {
}
