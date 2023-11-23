package com.project.business.model;

import com.project.business.model.base.BaseClass;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
public class Company extends BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String industry;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "company")
    private List<ProfExperience> profExperiences;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<OpenJob> openJobs;
    // Constructors can be omitted because Lombok generates them
}
