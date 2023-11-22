package edu.ea.jobservice.repository;

import edu.ea.jobservice.model.Job;
import lombok.extern.java.Log;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends ListCrudRepository<Job,Integer> {
    List<Job> findByCompanyName(@Param("companyName") String companyName);
    List<Job> findByUserId(Long userID);
    List<Job> findByAndAppliedUsersContaining(Long userID);
}
