package edu.miu.ea.ap.service.impl;

import edu.miu.ea.ap.model.domain.APLookup;
import edu.miu.ea.ap.model.dto.request.APLookupRequestDTO;
import edu.miu.ea.ap.model.dto.response.APLookupResponseDTO;
import edu.miu.ea.ap.repository.APLookupRepository;
import edu.miu.ea.ap.service.HCLookupService;
import edu.miu.ea.ap.service.HCUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.DiscriminatorValue;
import java.lang.reflect.Type;
import java.util.*;

@Service
public class HCLookupServiceImpl implements HCLookupService {

    @Autowired
    HCUserService userService;

    @Autowired
    APLookupRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<APLookupResponseDTO> getAll() {

        List<APLookup> lookups = repository.findAllByOrderByTypeAscSortOrderAscLabelEnAsc();
        Type listOfTypes = new TypeToken<List<APLookupResponseDTO>>() {}.getType();
        List<APLookupResponseDTO> lookupResponseDTOs = modelMapper.map(lookups, listOfTypes);
        return lookupResponseDTOs;
    }

    @Override
    public List<APLookupResponseDTO> getAllByType(String type, boolean validOnly) {

        Class<? extends APLookup> lookupClass = this.getLookupClassByDiscriminatorValue(type);
        if (lookupClass == null) {
            throw new RuntimeException("lookup type should be sent correctly");
        }

        List<APLookup> lookups = null;

        if (validOnly) {
            lookups = this.repository.findAllByTypeAndRetiredFalseOrderBySortOrderAscLabelEnAsc(type);
        } else {
            lookups = this.repository.findAllByTypeOrderBySortOrderAscLabelEnAsc(type);
        }

        Type listOfTypes = new TypeToken<List<APLookupResponseDTO>>() {}.getType();
        List<APLookupResponseDTO> lookupResponseDTOs = modelMapper.map(lookups, listOfTypes);

        return lookupResponseDTOs;

    }

    @Override
    public APLookupResponseDTO save(APLookupRequestDTO lookupRequestDTO) {

        if (lookupRequestDTO.getId() == null) {
            throw new RuntimeException("lookup id should be sent");
        }

        if (lookupRequestDTO.getType() == null) {
            throw new RuntimeException("lookup type should be sent");
        }

        Class<? extends APLookup> lookupClass = this.getLookupClassByDiscriminatorValue(lookupRequestDTO.getType());

        APLookup lookup = modelMapper.map(lookupRequestDTO, lookupClass);

        Optional<APLookup> oldLookup = repository.findById(lookup.getId());
        try {
            APLookup oldLookupValue = oldLookup.get();
            lookup.setCreatedAt(oldLookupValue.getCreatedAt());
        } catch (Exception ex) {
        }

        repository.save(lookup);

        Optional<APLookup> updatedLookup = repository.findById(lookup.getId());
        lookup = updatedLookup.get();

        APLookupResponseDTO lookupResponseDTO = modelMapper.map(lookup, APLookupResponseDTO.class);

        return lookupResponseDTO;

    }

    @Override
    public List<APLookupResponseDTO> save(List<APLookupRequestDTO> lookupRequestDTOs) {

        List<APLookupResponseDTO> lookupResponseDTOs = new ArrayList<>();

        for (APLookupRequestDTO lookupRequestDTO : lookupRequestDTOs) {
            lookupResponseDTOs.add(this.save(lookupRequestDTO));
        }

        return lookupResponseDTOs;

    }

    private Class<? extends APLookup> getLookupClassByDiscriminatorValue(String discriminatorValue) {

        Reflections reflections = new Reflections("");
        Set<Class<? extends APLookup>> subTypes = reflections.getSubTypesOf(APLookup.class);

        for (Class<? extends APLookup> clazz : subTypes) {
            String value = clazz.getAnnotation(DiscriminatorValue.class).value();
            if (discriminatorValue.equalsIgnoreCase(value)) {
                return clazz;
            }
        }

        return null;

    }

}
