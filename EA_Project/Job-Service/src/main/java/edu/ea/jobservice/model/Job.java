package edu.ea.jobservice.model;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

@Entity
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String companyName;
    private String industry;
    private String jobDescription;
    @Embedded
    private Address jobAddress;

    private Long userId;
    private  boolean deleted;

    @ElementCollection(fetch = FetchType.EAGER)
    private   List<Long>   appliedUsers;

}
