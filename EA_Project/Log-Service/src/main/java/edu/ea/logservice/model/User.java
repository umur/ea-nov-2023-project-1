package edu.ea.logservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="theUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
