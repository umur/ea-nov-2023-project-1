package ea.project.surevy_surveyresponse.repository;

import ea.project.surevy_surveyresponse.entity.Survey;
import ea.project.surevy_surveyresponse.entity.SurveyResponse;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface SurveyResponseRepo extends ListCrudRepository<SurveyResponse,Integer> {

    List<SurveyResponse> findAllBySurvey(Survey survey);
}
