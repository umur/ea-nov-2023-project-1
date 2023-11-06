package com.ea.project.service;

import com.ea.project.dto.InformationDto;
import com.ea.project.entity.*;

import java.util.List;

public interface InformationService {
    //add
    void create(Information  information);

    //del
    void delete(int id);

    //change
    void update(Information information);

    //search
    List<InformationDto> findAll();

    InformationDto findById(int id);
}
