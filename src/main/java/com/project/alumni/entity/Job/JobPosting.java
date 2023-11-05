package com.project.alumni.entity.Job;

import java.time.LocalDateTime;

import com.project.alumni.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "jobPostings")
@Getter
@Setter
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime creationDate;
    private PostingStatus status;

    @OneToOne
    private Job job;

    @OneToOne
    @JoinColumn(name = "userId")
    private User poster;

}
