package com.project.business.repository;

import com.project.business.model.ProfExperience;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfExperienceRepository extends ListCrudRepository<ProfExperience, Long> {
    List<ProfExperience> findAllByAlumniId(Long alumniId);
    List<ProfExperience> findAllByEndDateIsNullAndCompany_Industry(String industry);
    default void deleteAllByAlumniId(Long alumniIdLong){
        findAllByAlumniId(alumniIdLong).forEach(pe -> softDelete(pe));
    }
    default void softDelete(ProfExperience profExperience) {
        profExperience.setDeleted(true);
        save(profExperience);
    }

}
