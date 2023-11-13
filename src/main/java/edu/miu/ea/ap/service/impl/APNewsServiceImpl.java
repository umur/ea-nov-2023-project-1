package edu.miu.ea.ap.service.impl;

import edu.miu.ea.ap.model.domain.APNewsItem;
import edu.miu.ea.ap.model.dto.request.APNewsItemRequestDTO;
import edu.miu.ea.ap.model.dto.response.APNewsItemResponseDTO;
import edu.miu.ea.ap.repository.APNewsRepository;
import edu.miu.ea.ap.service.APNewsService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class APNewsServiceImpl implements APNewsService {

    @Autowired
    APNewsRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<APNewsItemResponseDTO> getAll() {
        List<APNewsItem> domainModels = repository.findAll();
        Type listOfDTOsType = new TypeToken<List<APNewsItemResponseDTO>>() {}.getType();
        List<APNewsItemResponseDTO> responseDTOs = modelMapper.map(domainModels, listOfDTOsType);
        return responseDTOs;
    }

    @Override
    public List<APNewsItemResponseDTO> getAllUnretired() {
        List<APNewsItem> domainModels = repository.findAllByRetiredFalse();
        Type dtoTypes = new TypeToken<List<APNewsItemResponseDTO>>() {}.getType();
        List<APNewsItemResponseDTO> responseDTOs = modelMapper.map(domainModels, dtoTypes);
        return responseDTOs;
    }

    @Override
    public APNewsItemResponseDTO getById(APNewsItemRequestDTO requestDTO) {
        APNewsItem domainModel = repository.findByIdAndRetiredFalse(requestDTO.getId());
        if (domainModel == null) {
            throw new RuntimeException("No record found");
        }
        APNewsItemResponseDTO responseDTO = modelMapper.map(domainModel, APNewsItemResponseDTO.class);
        return responseDTO;
    }

    @Override
    public APNewsItemResponseDTO save(APNewsItemRequestDTO requestDTO) {
        APNewsItem domainModel = modelMapper.map(requestDTO, APNewsItem.class);

        try {
            Optional<APNewsItem> oldDomainModel = repository.findById(domainModel.getId());
            APNewsItem oldDomainModelValue = oldDomainModel.get();
            domainModel.setCreatedAt(oldDomainModelValue.getCreatedAt());
        } catch (Exception ex) {
        }

        domainModel = repository.save(domainModel);

        Optional<APNewsItem> updatedDomainModel;
        try {
            updatedDomainModel = repository.findById(domainModel.getId());
        } catch (Exception ex) {
            throw new RuntimeException("could not extract ResultSet - custom");
        }

        domainModel = updatedDomainModel.get();
        APNewsItemResponseDTO responseDTO = modelMapper.map(domainModel, APNewsItemResponseDTO.class);

        return responseDTO;
    }

    @Override
    public void retire(APNewsItemRequestDTO requestDTO) {
        requestDTO.setRetired(true);
        this.setRetire(requestDTO);
    }

    @Override
    public void unretire(APNewsItemRequestDTO requestDTO) {
        requestDTO.setRetired(false);
        this.setRetire(requestDTO);
    }

    private void setRetire(APNewsItemRequestDTO requestDTO) {
        int count = repository.setRetired(requestDTO.getId(), requestDTO.isRetired());
        if (count <= 0) {
            throw new RuntimeException("Failed to set retired:" + requestDTO.isRetired() + ", for request dto model: [" + requestDTO.getClass().getSimpleName() + "] - id: " + requestDTO.getId());
        }
    }

}
