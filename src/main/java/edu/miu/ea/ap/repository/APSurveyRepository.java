package edu.miu.ea.ap.repository;

import edu.miu.ea.ap.helper.RefreshableCRUDRepository;
import edu.miu.ea.ap.model.domain.APSurvey;
import org.springframework.stereotype.Repository;

@Repository
public interface APSurveyRepository extends RefreshableCRUDRepository<APSurvey, Long> {
}
