package edu.miu.ea.ap.service.impl;

import edu.miu.ea.ap.model.domain.APMeeting;
import edu.miu.ea.ap.model.dto.request.APMeetingRequestDTO;
import edu.miu.ea.ap.model.dto.response.APMeetingResponseDTO;
import edu.miu.ea.ap.repository.APMeetingRepository;
import edu.miu.ea.ap.service.APMeetingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class APMeetingServiceImpl implements APMeetingService {

    @Autowired
    APMeetingRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<APMeetingResponseDTO> getAll() {
        List<APMeeting> domainModels = repository.findAll();
        Type listOfDTOsType = new TypeToken<List<APMeetingResponseDTO>>() {}.getType();
        List<APMeetingResponseDTO> responseDTOs = modelMapper.map(domainModels, listOfDTOsType);
        return responseDTOs;
    }

    @Override
    public List<APMeetingResponseDTO> getAllUnretired() {
        List<APMeeting> domainModels = repository.findAllByRetiredFalse();
        Type dtoTypes = new TypeToken<List<APMeetingResponseDTO>>() {}.getType();
        List<APMeetingResponseDTO> responseDTOs = modelMapper.map(domainModels, dtoTypes);
        return responseDTOs;
    }

    @Override
    public APMeetingResponseDTO getById(APMeetingRequestDTO requestDTO) {
        APMeeting domainModel = repository.findByIdAndRetiredFalse(requestDTO.getId());
        if (domainModel == null) {
            throw new RuntimeException("No record found");
        }
        APMeetingResponseDTO responseDTO = modelMapper.map(domainModel, APMeetingResponseDTO.class);
        return responseDTO;
    }

    @Override
    public APMeetingResponseDTO save(APMeetingRequestDTO requestDTO) {
        APMeeting domainModel = modelMapper.map(requestDTO, APMeeting.class);

        try {
            Optional<APMeeting> oldDomainModel = repository.findById(domainModel.getId());
            APMeeting oldDomainModelValue = oldDomainModel.get();
            domainModel.setCreatedAt(oldDomainModelValue.getCreatedAt());
        } catch (Exception ex) {
        }

        domainModel = repository.save(domainModel);

        Optional<APMeeting> updatedDomainModel;
        try {
            updatedDomainModel = repository.findById(domainModel.getId());
        } catch (Exception ex) {
            throw new RuntimeException("could not extract ResultSet - custom");
        }

        domainModel = updatedDomainModel.get();
        APMeetingResponseDTO responseDTO = modelMapper.map(domainModel, APMeetingResponseDTO.class);

        return responseDTO;
    }

    @Override
    public void retire(APMeetingRequestDTO requestDTO) {
        requestDTO.setRetired(true);
        this.setRetire(requestDTO);
    }

    @Override
    public void unretire(APMeetingRequestDTO requestDTO) {
        requestDTO.setRetired(false);
        this.setRetire(requestDTO);
    }

    private void setRetire(APMeetingRequestDTO requestDTO) {
        int count = repository.setRetired(requestDTO.getId(), requestDTO.isRetired());
        if (count <= 0) {
            throw new RuntimeException("Failed to set retired:" + requestDTO.isRetired() + ", for request dto model: [" + requestDTO.getClass().getSimpleName() + "] - id: " + requestDTO.getId());
        }
    }

}
