package ea.project.surevy_surveyresponse.repository;


import ea.project.surevy_surveyresponse.entity.Survey;
import ea.project.surevy_surveyresponse.entity.SurveyResponse;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepo extends ListCrudRepository<Survey, Integer> {



}
