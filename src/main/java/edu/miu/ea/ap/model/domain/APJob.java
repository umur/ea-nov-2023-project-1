package edu.miu.ea.ap.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "AP_JOB")
public class APJob extends APBaseEntityWithIdAuto {

    private String description;
    private APCompany company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADVERTISER_ID")
    private APUser advertiser;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "AP_JOB_APPLICANTS",
            joinColumns = @JoinColumn(name = "JOB_ID"),
            inverseJoinColumns = @JoinColumn(name = "APPLICANT_ID")
    )
    private Set<APUser> applicants;

}
