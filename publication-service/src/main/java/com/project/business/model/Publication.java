package com.project.business.model;
import com.project.business.model.base.BaseClass;
import com.project.business.model.type.PublicationType;
import lombok.Getter;import lombok.Setter;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
public class Publication extends BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "publication_type_id")
    private PublicationType publicationType;

    // Constructors can be omitted because Lombok generates them
}
