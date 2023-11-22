package com.alumni.service;


import com.alumni.dto.SurveyAnswerDto;
import com.alumni.dto.ExternalUserDto;
import com.alumni.entity.Survey;
import com.alumni.entity.SurveyAnswer;
import com.alumni.repository.SurveyAnswerRpo;
import com.alumni.repository.SurveyRepo;
import com.alumni.service.external.UsersClient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyAnswerServiceImpl implements SurveyAnswerService {
    private final SurveyAnswerRpo SurveyAnswerRepo;
    private final UsersClient usersClient;
    private final SurveyRepo surveyRepo;
    private final ModelMapper modelMapper;

    @Override
    public void save(SurveyAnswerDto surveyAnswerDto) {
        var user = usersClient.findById(surveyAnswerDto.getUser().getId()).orElseThrow(
                () -> new HttpStatusCodeException(HttpStatus.NOT_FOUND, "User not found") {}
        );
        surveyAnswerDto.setUser(modelMapper.map(user, ExternalUserDto.class));
        SurveyAnswerRepo.save(modelMapper.map(surveyAnswerDto, SurveyAnswer.class));
    }

    @Override
    public List<SurveyAnswerDto> findAll() {
        List<SurveyAnswer> surveyAnswers = SurveyAnswerRepo.findAll();
        var res = new ArrayList<SurveyAnswerDto>();
        surveyAnswers.forEach(surveyAnswer ->{
                if(surveyAnswer.getDeleted() == 0){
                    res.add(modelMapper.map(surveyAnswer, SurveyAnswerDto.class));
                }
        });
        return res;
    }

    @Override
    public SurveyAnswerDto findById(Long id) {
        var surveyAnswer = SurveyAnswerRepo.findById(id).orElseThrow(
                () -> new HttpStatusCodeException(HttpStatus.NOT_FOUND, "SurveyAnswer not found") {}
        );
        return modelMapper.map(surveyAnswer, SurveyAnswerDto.class);
    }

    @Override
    public void update(Long id, SurveyAnswerDto surveyAnswerDto) {
        var surveyAnswer1 = SurveyAnswerRepo.findById(id);
        if (surveyAnswer1.isPresent() && surveyAnswer1.get().getDeleted() == 0) {
//            User user = userRepo.findById(surveyAnswerDto.getUser().getId()).get();
            Survey survey = surveyRepo.findById(surveyAnswerDto.getSurvey().getId()).get();
            SurveyAnswer surveyAnswer2 = modelMapper.map(surveyAnswerDto, SurveyAnswer.class);
            surveyAnswer2.setId(id);
            surveyAnswer2.setAnswer(surveyAnswerDto.getAnswer());
            surveyAnswer2.setSurvey(survey);
            surveyAnswer2.setUserId(surveyAnswerDto.getUser().getId());
            SurveyAnswerRepo.save(surveyAnswer2);
        }
    }

    @Override
    public void deleteById(Long id) {
        var surveyAnswer = SurveyAnswerRepo.findById(id);
        if (surveyAnswer.isPresent()) {
            surveyAnswer.get().setDeleted(1);
        }
    }
}
