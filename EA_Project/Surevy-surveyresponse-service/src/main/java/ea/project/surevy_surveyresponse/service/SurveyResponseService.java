package ea.project.surevy_surveyresponse.service;

import ea.project.surevy_surveyresponse.entity.SurveyResponse;

import java.util.List;

public interface SurveyResponseService {
    SurveyResponse addResponse(Integer surveyId,SurveyResponse surveyResponse) throws Exception;
    List<SurveyResponse> findAllResponse(Integer surveyId) throws Exception;

}
