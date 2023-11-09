package com.example.alumniproject.repository;

import com.example.alumniproject.entity.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends ListCrudRepository<Job, Long> {
    List<Job> getJobsByOrganization(String organization);

    List<Job> getJobsByLocationState(String state);

    List<Job> getJobsByLocationCity(String city);
    @Query(value="Select a from Job a where a.deleteYn = false "
            + "and (:#{#city} is null or a.location.city like :#{#city}) "
            + "and (:#{#state} is null or a.location.state like :#{#state}) "
            + "and (:#{#organization} is null or a.organization like :#{#organization}) ")
    List<Job> getJobsByLocationCityOrLocationStateOrOrganization(String city, String state, String organization);

}
