package com.alumni.jobservice.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    private Date modifiedDate;

    private Boolean showYn;

    private Boolean deleteYn;


    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
        this.modifiedDate = new Date();
        this.showYn = true;
        this.deleteYn = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedDate = new Date();
    }

    @PreRemove
    protected void onDelete(){
        this.showYn = false;
        this.deleteYn = true;
    }
}
