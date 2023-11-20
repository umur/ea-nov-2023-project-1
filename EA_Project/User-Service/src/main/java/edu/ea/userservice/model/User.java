package edu.ea.userservice.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String password;
    private String email;
    @Embedded
    private Address address;
    private boolean isActive;
    private String cv;
    private boolean isDeleted;
    @OneToOne
    @JoinColumn(name="role_id")
    Role role;



}
