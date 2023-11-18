package com.project.alumni.entity.Job;

import java.time.LocalDateTime;

import com.project.alumni.entity.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "postings")
@Getter
@Setter
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime creationDate;
    private PostingStatus status;

    @OneToOne
    private Job job;

    @ManyToOne
    @JoinColumn(name = "posterId")
    private User poster;

}
