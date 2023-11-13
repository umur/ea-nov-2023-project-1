package edu.miu.ea.ap.service.impl;

import edu.miu.ea.ap.model.domain.APSurveyResponse;
import edu.miu.ea.ap.model.dto.request.APSurveyResponseRequestDTO;
import edu.miu.ea.ap.model.dto.response.APSurveyResponseResponseDTO;
import edu.miu.ea.ap.repository.APSurveyResponseRepository;
import edu.miu.ea.ap.service.APSurveyResponseService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class APSurveyResponseServiceImpl implements APSurveyResponseService {

    @Autowired
    APSurveyResponseRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<APSurveyResponseResponseDTO> getAll() {
        List<APSurveyResponse> domainModels = repository.findAll();
        Type listOfDTOsType = new TypeToken<List<APSurveyResponseResponseDTO>>() {}.getType();
        List<APSurveyResponseResponseDTO> responseDTOs = modelMapper.map(domainModels, listOfDTOsType);
        return responseDTOs;
    }

    @Override
    public List<APSurveyResponseResponseDTO> getAllUnretired() {
        List<APSurveyResponse> domainModels = repository.findAllByRetiredFalse();
        Type dtoTypes = new TypeToken<List<APSurveyResponseResponseDTO>>() {}.getType();
        List<APSurveyResponseResponseDTO> responseDTOs = modelMapper.map(domainModels, dtoTypes);
        return responseDTOs;
    }

    @Override
    public APSurveyResponseResponseDTO getById(APSurveyResponseRequestDTO requestDTO) {
        APSurveyResponse domainModel = repository.findByIdAndRetiredFalse(requestDTO.getId());
        if (domainModel == null) {
            throw new RuntimeException("No record found");
        }
        APSurveyResponseResponseDTO responseDTO = modelMapper.map(domainModel, APSurveyResponseResponseDTO.class);
        return responseDTO;
    }

    @Override
    public APSurveyResponseResponseDTO save(APSurveyResponseRequestDTO requestDTO) {
        APSurveyResponse domainModel = modelMapper.map(requestDTO, APSurveyResponse.class);

        try {
            Optional<APSurveyResponse> oldDomainModel = repository.findById(domainModel.getId());
            APSurveyResponse oldDomainModelValue = oldDomainModel.get();
            domainModel.setCreatedAt(oldDomainModelValue.getCreatedAt());
        } catch (Exception ex) {
        }

        domainModel = repository.save(domainModel);

        Optional<APSurveyResponse> updatedDomainModel;
        try {
            updatedDomainModel = repository.findById(domainModel.getId());
        } catch (Exception ex) {
            throw new RuntimeException("could not extract ResultSet - custom");
        }

        domainModel = updatedDomainModel.get();
        APSurveyResponseResponseDTO responseDTO = modelMapper.map(domainModel, APSurveyResponseResponseDTO.class);

        return responseDTO;
    }

    @Override
    public void retire(APSurveyResponseRequestDTO requestDTO) {
        requestDTO.setRetired(true);
        this.setRetire(requestDTO);
    }

    @Override
    public void unretire(APSurveyResponseRequestDTO requestDTO) {
        requestDTO.setRetired(false);
        this.setRetire(requestDTO);
    }

    private void setRetire(APSurveyResponseRequestDTO requestDTO) {
        int count = repository.setRetired(requestDTO.getId(), requestDTO.isRetired());
        if (count <= 0) {
            throw new RuntimeException("Failed to set retired:" + requestDTO.isRetired() + ", for request dto model: [" + requestDTO.getClass().getSimpleName() + "] - id: " + requestDTO.getId());
        }
    }

}
