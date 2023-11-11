package edu.miu.ea.ap.service.impl;

import edu.miu.ea.ap.model.domain.APJob;
import edu.miu.ea.ap.model.dto.request.APJobRequestDTO;
import edu.miu.ea.ap.model.dto.response.APJobResponseDTO;
import edu.miu.ea.ap.repository.APJobRepository;
import edu.miu.ea.ap.service.APJobService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class APJobServiceImpl implements APJobService {

    @Autowired
    APJobRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<APJobResponseDTO> getAll() {
        List<APJob> domainModels = repository.findAll();
        Type listOfDTOsType = new TypeToken<List<APJobResponseDTO>>() {}.getType();
        List<APJobResponseDTO> responseDTOs = modelMapper.map(domainModels, listOfDTOsType);
        return responseDTOs;
    }

    @Override
    public List<APJobResponseDTO> getAllUnretired() {
        List<APJob> domainModels = repository.findAllByRetiredFalse();
        Type dtoTypes = new TypeToken<List<APJobResponseDTO>>() {}.getType();
        List<APJobResponseDTO> responseDTOs = modelMapper.map(domainModels, dtoTypes);
        return responseDTOs;
    }

    @Override
    public APJobResponseDTO getById(APJobRequestDTO requestDTO) {
        APJob domainModel = repository.findByIdAndRetiredFalse(requestDTO.getId());
        if (domainModel == null) {
            throw new RuntimeException("No record found");
        }
        APJobResponseDTO responseDTO = modelMapper.map(domainModel, APJobResponseDTO.class);
        return responseDTO;
    }

    @Override
    public APJobResponseDTO save(APJobRequestDTO requestDTO) {
        APJob domainModel = modelMapper.map(requestDTO, APJob.class);

        try {
            Optional<APJob> oldDomainModel = repository.findById(domainModel.getId());
            APJob oldDomainModelValue = oldDomainModel.get();
            domainModel.setCreatedAt(oldDomainModelValue.getCreatedAt());
        } catch (Exception ex) {
        }

        domainModel = repository.save(domainModel);

        Optional<APJob> updatedDomainModel;
        try {
            updatedDomainModel = repository.findById(domainModel.getId());
        } catch (Exception ex) {
            throw new RuntimeException("could not extract ResultSet - custom");
        }

        domainModel = updatedDomainModel.get();
        APJobResponseDTO responseDTO = modelMapper.map(domainModel, APJobResponseDTO.class);

        return responseDTO;
    }

    @Override
    public void retire(APJobRequestDTO requestDTO) {
        requestDTO.setRetired(true);
        this.setRetire(requestDTO);
    }

    @Override
    public void unretire(APJobRequestDTO requestDTO) {
        requestDTO.setRetired(false);
        this.setRetire(requestDTO);
    }

    private void setRetire(APJobRequestDTO requestDTO) {
        int count = repository.setRetired(requestDTO.getId(), requestDTO.isRetired());
        if (count <= 0) {
            throw new RuntimeException("Failed to set retired:" + requestDTO.isRetired() + ", for request dto model: [" + requestDTO.getClass().getSimpleName() + "] - id: " + requestDTO.getId());
        }
    }

}
