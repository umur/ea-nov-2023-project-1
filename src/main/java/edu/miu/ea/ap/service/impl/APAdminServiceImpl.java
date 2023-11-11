package edu.miu.ea.ap.service.impl;

import edu.miu.ea.ap.repository.APAdminRepository;
import edu.miu.ea.ap.service.APAdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APAdminServiceImpl implements APAdminService {

    @Autowired
    APAdminRepository repository;

    @Autowired
    ModelMapper modelMapper;

}
