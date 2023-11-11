package edu.miu.ea.ap.service.impl;

import edu.miu.ea.ap.repository.APMeetingRepository;
import edu.miu.ea.ap.service.APMeetingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APMeetingServiceImpl implements APMeetingService {

    @Autowired
    APMeetingRepository repository;

    @Autowired
    ModelMapper modelMapper;

}
