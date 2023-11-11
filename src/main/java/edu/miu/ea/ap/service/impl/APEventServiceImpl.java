package edu.miu.ea.ap.service.impl;

import edu.miu.ea.ap.repository.APEventRepository;
import edu.miu.ea.ap.service.APEventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APEventServiceImpl implements APEventService {

    @Autowired
    APEventRepository repository;

    @Autowired
    ModelMapper modelMapper;

}
