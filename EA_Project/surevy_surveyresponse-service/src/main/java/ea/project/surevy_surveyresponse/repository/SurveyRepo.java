package ea.project.surevy_surveyresponse.repository;


import ea.project.surevy_surveyresponse.entity.Survey;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepo extends ListCrudRepository<Survey, Integer> {

}
