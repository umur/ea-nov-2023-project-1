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
    private String email;
    private String password;
    private boolean active;
    private boolean deleted;
    @ManyToOne
    @JoinColumn(name="role_id")
    Role role;



}
