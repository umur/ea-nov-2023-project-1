package com.alumni.jobservice.Repo;

import com.alumni.jobservice.Entity.Job;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends ListCrudRepository<Job, Long> {
//    List<Job> getJobsByOrganization(String organization);
//
//    List<Job> getJobsByLocationState(String state);
//
//    List<Job> getJobsByLocationCity(String city);
//    @Query(value="Select a from Job a where a.deleteYn = false "
//            + "and (:#{#city} is null or a.location.city like :#{#city}) "
//            + "and (:#{#state} is null or a.location.state like :#{#state}) "
//            + "and (:#{#organization} is null or a.organization like :#{#organization}) ")
//    List<Job> getJobsByLocationCityOrLocationStateOrOrganization(String city, String state, String organization);

}
