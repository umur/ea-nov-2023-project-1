package com.miu.alumnimanagementportal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class ProfileEntity extends BaseEntity {
    @OneToOne(mappedBy = "profileEntity", optional = false, orphanRemoval = true)
    private UserEntity userEntity;

}
