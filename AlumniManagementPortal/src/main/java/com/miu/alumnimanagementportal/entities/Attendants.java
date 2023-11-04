package com.miu.alumnimanagementportal.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Attendants extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;

/*    @ManyToOne
    @JoinColumn(name = "user_id")
    private List<UserEntity> user;*/

    @Column(columnDefinition = "boolean default false")
    private boolean is_confirmed;

}
