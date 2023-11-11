package edu.miu.ea.ap.service.impl;

import edu.miu.ea.ap.repository.APJobRepository;
import edu.miu.ea.ap.service.APJobService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APJobServiceImpl implements APJobService {

    @Autowired
    APJobRepository repository;

    @Autowired
    ModelMapper modelMapper;

}
