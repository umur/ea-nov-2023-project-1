package com.project.business.model;
import com.project.business.model.base.BaseClass;
import lombok.Getter;import lombok.Setter;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
public class Feedback extends BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Long alumniId;

   
}
