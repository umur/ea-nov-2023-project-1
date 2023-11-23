package com.project.business.model;
import com.project.business.model.base.BaseClass;
import lombok.Getter;import lombok.Setter;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
public class OpenJob extends BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime openDate;
    private String description;

    private Long creator_id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "openJob")
    private List<Application> applications;
}
