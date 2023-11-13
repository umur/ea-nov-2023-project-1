package edu.miu.ea.ap.service.impl;

import edu.miu.ea.ap.model.domain.APEvent;
import edu.miu.ea.ap.model.dto.request.APEventRequestDTO;
import edu.miu.ea.ap.model.dto.response.APEventResponseDTO;
import edu.miu.ea.ap.repository.APEventRepository;
import edu.miu.ea.ap.service.APEventService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class APEventServiceImpl implements APEventService {

    @Autowired
    APEventRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<APEventResponseDTO> getAll() {
        List<APEvent> domainModels = repository.findAll();
        Type listOfDTOsType = new TypeToken<List<APEventResponseDTO>>() {}.getType();
        List<APEventResponseDTO> responseDTOs = modelMapper.map(domainModels, listOfDTOsType);
        return responseDTOs;
    }

    @Override
    public List<APEventResponseDTO> getAllUnretired() {
        List<APEvent> domainModels = repository.findAllByRetiredFalse();
        Type dtoTypes = new TypeToken<List<APEventResponseDTO>>() {}.getType();
        List<APEventResponseDTO> responseDTOs = modelMapper.map(domainModels, dtoTypes);
        return responseDTOs;
    }

    @Override
    public APEventResponseDTO getById(APEventRequestDTO requestDTO) {
        APEvent domainModel = repository.findByIdAndRetiredFalse(requestDTO.getId());
        if (domainModel == null) {
            throw new RuntimeException("No record found");
        }
        APEventResponseDTO responseDTO = modelMapper.map(domainModel, APEventResponseDTO.class);
        return responseDTO;
    }

    @Override
    public APEventResponseDTO save(APEventRequestDTO requestDTO) {
        APEvent domainModel = modelMapper.map(requestDTO, APEvent.class);

        try {
            Optional<APEvent> oldDomainModel = repository.findById(domainModel.getId());
            APEvent oldDomainModelValue = oldDomainModel.get();
            domainModel.setCreatedAt(oldDomainModelValue.getCreatedAt());
        } catch (Exception ex) {
        }

        domainModel = repository.save(domainModel);

        Optional<APEvent> updatedDomainModel;
        try {
            updatedDomainModel = repository.findById(domainModel.getId());
        } catch (Exception ex) {
            throw new RuntimeException("could not extract ResultSet - custom");
        }

        domainModel = updatedDomainModel.get();
        APEventResponseDTO responseDTO = modelMapper.map(domainModel, APEventResponseDTO.class);

        return responseDTO;
    }

    @Override
    public void retire(APEventRequestDTO requestDTO) {
        requestDTO.setRetired(true);
        this.setRetire(requestDTO);
    }

    @Override
    public void unretire(APEventRequestDTO requestDTO) {
        requestDTO.setRetired(false);
        this.setRetire(requestDTO);
    }

    private void setRetire(APEventRequestDTO requestDTO) {
        int count = repository.setRetired(requestDTO.getId(), requestDTO.isRetired());
        if (count <= 0) {
            throw new RuntimeException("Failed to set retired:" + requestDTO.isRetired() + ", for request dto model: [" + requestDTO.getClass().getSimpleName() + "] - id: " + requestDTO.getId());
        }
    }

}
