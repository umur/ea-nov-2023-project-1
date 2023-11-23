package com.alumni.jobservice.Repo;

import com.alumni.jobservice.Entity.Experience;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExperienceRepo extends ListCrudRepository<Experience, Long> {

    @Query(value = "select a from Experience a where a.deleteYn = false and a.showYn = true and a.id = ?1")
    Optional<Experience> findById (Long id);

    @Query(value = "select a from Experience a where a.deleteYn = false and a.showYn = true")
    List<Experience> findAll();

    @Query(value = "select a from Experience a where a.deleteYn = false and a.showYn and a.profileId = ?1")
    List<Experience> findExperienceByProfileId(Long profileId);
}
