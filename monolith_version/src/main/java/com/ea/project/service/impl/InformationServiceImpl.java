package com.ea.project.service.impl;

import com.ea.project.dto.InformationDto;
import com.ea.project.entity.Information;
import com.ea.project.respository.InformationRepo;
import com.ea.project.service.InformationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InformationServiceImpl implements InformationService {

    private final InformationRepo informationRepo;
    private final ModelMapper modelMapper;

    @Override
    public void create(InformationDto information) {
        informationRepo.save(information.getInformation(modelMapper));
    }

    @Override
    public void delete(int id) {
        informationRepo.deleteById(id);
    }

    @Override
    public void update(InformationDto information) {
        informationRepo.save(information.getInformation(modelMapper));
    }

    @Override
    public List<InformationDto> findAll() {
        List<Information> informationListList = informationRepo.findAll();
        var result= new ArrayList<InformationDto>();

        informationListList.forEach(product -> {
            var informationDto=modelMapper.map(product, InformationDto.class);
            result.add(informationDto);
        });
        return result;
    }

    @Override
    public InformationDto findById(int id) {
        var information=informationRepo.findById(id).orElseThrow(()->new RuntimeException("information not found"));
        var result = modelMapper.map(information, InformationDto.class);
        return result;
    }
}
