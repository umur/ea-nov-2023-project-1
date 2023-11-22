package ea.project.surevy_surveyresponse.service.impl;

import ea.project.surevy_surveyresponse.entity.Survey;
import ea.project.surevy_surveyresponse.entity.SurveyResponse;
import ea.project.surevy_surveyresponse.repository.SurveyRepo;
import ea.project.surevy_surveyresponse.repository.SurveyResponseRepo;
import ea.project.surevy_surveyresponse.service.SurveyResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SurveyResponseServiceImpl  implements SurveyResponseService {
    private final SurveyResponseRepo surveyResponseRepo;
    private final SurveyRepo surveyRepo;
    private final JwtService jwtService;

    @Override
    public SurveyResponse addResponse(Integer surveyId, SurveyResponse surveyResponse) throws Exception {
        Optional<Survey> byId = surveyRepo.findById(surveyId);
        if(!byId.isPresent())
            throw new Exception("Survey not found ");
        surveyResponse.setSurvey(byId.get());
        Long userIdFromToken = jwtService.getUserIdFromToken();
        surveyResponse.setUserId(userIdFromToken);
        return surveyResponseRepo.save(surveyResponse);
    }

    @Override
    public List<SurveyResponse> findAllResponse(Integer surveyId) throws Exception {
        Optional<Survey> byId = surveyRepo.findById(surveyId);
        if(!byId.isPresent())
            throw new Exception("Survey not found ");
        return surveyResponseRepo.findAllBySurvey(byId.get());
    }
}
