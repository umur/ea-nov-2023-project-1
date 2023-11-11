package edu.miu.ea.ap.service.impl;

import edu.miu.ea.ap.repository.APNewsRepository;
import edu.miu.ea.ap.service.APNewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APNewsServiceImpl implements APNewsService {

    @Autowired
    APNewsRepository repository;

    @Autowired
    ModelMapper modelMapper;

}
