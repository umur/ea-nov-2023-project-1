package com.alumni.JobService.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.alumni.JobService.entity.Posting;

@Repository
public interface PostingRepository extends ListCrudRepository<Posting, Long> {

}
