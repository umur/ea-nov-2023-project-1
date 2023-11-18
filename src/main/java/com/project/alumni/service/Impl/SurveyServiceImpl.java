package com.project.alumni.service.Impl;

import com.project.alumni.dto.SurveyDto;
import com.project.alumni.dto.user.UserFullDetailsDto;
import com.project.alumni.entity.Survey;
import com.project.alumni.entity.user.User;
import com.project.alumni.repository.SurveyRepo;
import com.project.alumni.repository.user.UserRepository;
import com.project.alumni.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepo surveyRepo;
    private final UserRepository userRepo;
    private final ModelMapper modelMapper;

    @Override
    public void save(SurveyDto surveyDto) {
        User user = userRepo.findById(surveyDto.getUser().getId()).orElseThrow(
                () -> new HttpStatusCodeException(HttpStatus.NOT_FOUND, "User not found") {
                }
        );
        surveyDto.setUser(modelMapper.map(user, UserFullDetailsDto.class));
        surveyRepo.save(modelMapper.map(surveyDto, Survey.class));
    }

    @Override
    public List<SurveyDto> findAll() {
        List<Survey> surveys = surveyRepo.findAll();
        var res = new ArrayList<SurveyDto>();
        surveys.forEach(e -> {
            if (e.getDeleted() == 0) {
                res.add(modelMapper.map(e, SurveyDto.class));
            }
        });
        return res;
    }

    @Override
    public SurveyDto findById(Long id) {
        var res = surveyRepo.findById(id);
        if (!res.isPresent() || res.get().getDeleted() == 1) {
            return null;
        }
        return modelMapper.map(res, SurveyDto.class);
    }

    @Override
    public void update(Long id, SurveyDto surveyDto) {
        var survey = surveyRepo.findById(id);
        if (survey.isPresent() && survey.get().getDeleted() == 0) {
            User user = userRepo.findById(surveyDto.getUser().getId()).get();
            Survey var1 = modelMapper.map(surveyDto, Survey.class);
            var1.setId(id);
            var1.setTitle(surveyDto.getTitle());
            var1.setDescription(surveyDto.getDescription());
            var1.setStartDate(surveyDto.getStartDate());
            var1.setEndDate(surveyDto.getEndDate());
            var1.setUser(user);
            surveyRepo.save(var1);
        }
    }

    @Override
    public void delete(Long id) {
        var survey = surveyRepo.findById(id);
        if (survey.isPresent()) {
            survey.get().setDeleted(1);
        }
    }
}
