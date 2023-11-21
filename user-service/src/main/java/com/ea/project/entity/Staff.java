package com.ea.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "staffs")
public class Staff extends User{

    private String title;
}
