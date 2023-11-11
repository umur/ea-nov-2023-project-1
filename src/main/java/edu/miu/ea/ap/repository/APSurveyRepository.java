package edu.miu.ea.ap.repository;

import edu.miu.ea.ap.helper.RefreshableCRUDRepository;
import edu.miu.ea.ap.model.domain.APSurvey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface APSurveyRepository extends RefreshableCRUDRepository<APSurvey, Long> {

    List<APSurvey> findAll();
    List<APSurvey> findAllByRetiredFalse();
    APSurvey findByIdAndRetiredFalse(Long id);

    @Modifying
    @Transactional
    @Query("update APSurvey m set m.retired = :retired where m.id = :id")
    int setRetired(@Param("id") Long id, @Param("retired") boolean retired);

}
