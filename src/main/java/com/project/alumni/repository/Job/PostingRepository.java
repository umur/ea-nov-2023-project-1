package com.project.alumni.repository.Job;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.project.alumni.entity.Job.Posting;

@Repository
public interface PostingRepository extends ListCrudRepository<Posting, Long> {

}
