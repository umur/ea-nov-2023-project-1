package edu.ea.jobservice.repository;

import com.example.EA_project.entity.Job;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends ListCrudRepository<Job,Integer> {
    List<Job> findByCompanyName(@Param("companyName") String companyName);
}
