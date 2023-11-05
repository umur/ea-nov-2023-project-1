package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.dtos.AttendantDto;
import com.miu.alumnimanagementportal.services.AttendantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AttendantServiceImpl implements AttendantService {
    @Override
    public void create(AttendantDto attendantDto) {

    }

    @Override
    public List<AttendantDto> findAll() {
        return null;
    }

    @Override
    public AttendantDto update(AttendantDto attendantDto, Long id) {
        return null;
    }

    @Override
    public AttendantDto getAttendantById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
