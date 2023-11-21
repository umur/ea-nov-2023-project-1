package com.ea.project.service;

import com.ea.project.dto.InformationDto;

import java.util.List;

public interface InformationService {
    //add
    void create(InformationDto  information);

    //del
    void delete(int id);

    //change
    void update(InformationDto information);

    //search
    List<InformationDto> findAll();

    InformationDto findById(int id);
}
