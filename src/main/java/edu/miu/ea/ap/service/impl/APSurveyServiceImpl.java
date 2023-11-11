package edu.miu.ea.ap.service.impl;

import edu.miu.ea.ap.repository.APSurveyRepository;
import edu.miu.ea.ap.service.APSurveyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APSurveyServiceImpl implements APSurveyService {

    @Autowired
    APSurveyRepository repository;

    @Autowired
    ModelMapper modelMapper;

}
