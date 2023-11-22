package ea.project.surevy_surveyresponse.controller;

import ea.project.surevy_surveyresponse.entity.SurveyResponse;
import ea.project.surevy_surveyresponse.service.SurveyResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/surveyResponse")
public class SurveyResponseController {
    private final SurveyResponseService surveyResponseService;

    @PostMapping("/{surveyId}")
    public SurveyResponse addResponse( @PathVariable Integer surveyId ,@RequestBody SurveyResponse surveyResponse) throws Exception {
      return   surveyResponseService.addResponse(surveyId,surveyResponse);
    }


}
