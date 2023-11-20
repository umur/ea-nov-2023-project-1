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
    private String graduationYear;
    private String description;
    private String category;
    private String industry;


    private int addressId;

    @ElementCollection
    private   List<String> courses;

    private int jobId;

    private int userId;

}

