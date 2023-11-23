package ea.project.student.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
public class Student  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String cv;
    private String image;
    private String graduationYear;
    private String description;
    private String industry;
    private String category;

    @Embedded
    private Address address;

    @ElementCollection
    private   List<String> courses;

    private int jobId;

    private Long userId;
    private boolean deleted;

}

