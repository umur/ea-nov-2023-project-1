package edu.miu.ea.ap.service.impl;

import edu.miu.ea.ap.model.domain.APSurvey;
import edu.miu.ea.ap.model.dto.request.APSurveyRequestDTO;
import edu.miu.ea.ap.model.dto.response.APSurveyResponseDTO;
import edu.miu.ea.ap.repository.APSurveyRepository;
import edu.miu.ea.ap.service.APSurveyService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class APSurveyServiceImpl implements APSurveyService {

    @Autowired
    APSurveyRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<APSurveyResponseDTO> getAll() {
        List<APSurvey> domainModels = repository.findAll();
        Type listOfDTOsType = new TypeToken<List<APSurveyResponseDTO>>() {}.getType();
        List<APSurveyResponseDTO> responseDTOs = modelMapper.map(domainModels, listOfDTOsType);
        return responseDTOs;
    }

    @Override
    public List<APSurveyResponseDTO> getAllUnretired() {
        List<APSurvey> domainModels = repository.findAllByRetiredFalse();
        Type dtoTypes = new TypeToken<List<APSurveyResponseDTO>>() {}.getType();
        List<APSurveyResponseDTO> responseDTOs = modelMapper.map(domainModels, dtoTypes);
        return responseDTOs;
    }

    @Override
    public APSurveyResponseDTO getById(APSurveyRequestDTO requestDTO) {
        APSurvey domainModel = repository.findByIdAndRetiredFalse(requestDTO.getId());
        if (domainModel == null) {
            throw new RuntimeException("No record found");
        }
        APSurveyResponseDTO responseDTO = modelMapper.map(domainModel, APSurveyResponseDTO.class);
        return responseDTO;
    }

    @Override
    public APSurveyResponseDTO save(APSurveyRequestDTO requestDTO) {
        APSurvey domainModel = modelMapper.map(requestDTO, APSurvey.class);

        try {
            Optional<APSurvey> oldDomainModel = repository.findById(domainModel.getId());
            APSurvey oldDomainModelValue = oldDomainModel.get();
            domainModel.setCreatedAt(oldDomainModelValue.getCreatedAt());
        } catch (Exception ex) {
        }

        domainModel = repository.save(domainModel);

        Optional<APSurvey> updatedDomainModel;
        try {
            updatedDomainModel = repository.findById(domainModel.getId());
        } catch (Exception ex) {
            throw new RuntimeException("could not extract ResultSet - custom");
        }

        domainModel = updatedDomainModel.get();
        APSurveyResponseDTO responseDTO = modelMapper.map(domainModel, APSurveyResponseDTO.class);

        return responseDTO;
    }

    @Override
    public void retire(APSurveyRequestDTO requestDTO) {
        requestDTO.setRetired(true);
        this.setRetire(requestDTO);
    }

    @Override
    public void unretire(APSurveyRequestDTO requestDTO) {
        requestDTO.setRetired(false);
        this.setRetire(requestDTO);
    }

    private void setRetire(APSurveyRequestDTO requestDTO) {
        int count = repository.setRetired(requestDTO.getId(), requestDTO.isRetired());
        if (count <= 0) {
            throw new RuntimeException("Failed to set retired:" + requestDTO.isRetired() + ", for request dto model: [" + requestDTO.getClass().getSimpleName() + "] - id: " + requestDTO.getId());
        }
    }

}
