package com.miu.alumnimanagementportal.services.impl;

import com.miu.alumnimanagementportal.dtos.RoleDto;
import com.miu.alumnimanagementportal.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public void create(RoleDto roleDto) {

    }

    @Override
    public List<RoleDto> findAll() {
        return null;
    }

    @Override
    public RoleDto update(RoleDto roleDto, Long id) {
        return null;
    }

    @Override
    public RoleDto getRoleById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
