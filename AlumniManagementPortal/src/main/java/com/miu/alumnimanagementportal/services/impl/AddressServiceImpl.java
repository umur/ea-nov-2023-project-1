package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.dtos.AddressDto;
import com.miu.alumnimanagementportal.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    @Override
    public void create(AddressDto address) {

    }

    @Override
    public List<AddressDto> findAll() {
        return null;
    }

    @Override
    public AddressDto update(AddressDto address, Long id) {
        return null;
    }

    @Override
    public AddressDto getAddressById(Long id) {
        return null;
    }

    @Override
    public void delete(Long address) {

    }
}
