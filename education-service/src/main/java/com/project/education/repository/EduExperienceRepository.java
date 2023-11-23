package com.project.education.repository;

import com.project.education.model.EduExperience;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EduExperienceRepository extends ListCrudRepository<EduExperience, Long> {
//    Optional<EduExperience> findByAlumni_Id(Long alumniId);
    @Override
    default void delete(EduExperience eduExperience) {
        eduExperience.setDeleted(true);
    }
}
