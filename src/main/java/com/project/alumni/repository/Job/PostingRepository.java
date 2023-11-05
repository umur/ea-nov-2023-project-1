package com.project.alumni.repository.Job;

import org.springframework.data.repository.CrudRepository;

import com.project.alumni.entity.Job.Posting;

public interface PostingRepository extends CrudRepository<Posting, Long> {

}
