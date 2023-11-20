package ea.project.surevy_surveyresponse.service;

import ea.project.surevy_surveyresponse.entity.Survey;
import ea.project.surevy_surveyresponse.entity.SurveyResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SurveyService {
    public void add(Survey survey);
    public void remove(int id);
    public List<Survey> findAll();
    public void update(Survey survey);
    public void addResponse(int survey_id, SurveyResponse surveyResponse);
	public Survey getById(int id);

}
