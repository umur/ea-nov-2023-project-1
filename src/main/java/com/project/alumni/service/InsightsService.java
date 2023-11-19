package com.project.alumni.service;

import com.project.alumni.dto.InsightsDto;
import java.util.List;
public interface InsightsService {

    void save(InsightsDto insightsDto);
    List<InsightsDto> findAll();
    InsightsDto findById(Long id);
    void update(Long id, InsightsDto insightsDto);
    void delete(Long id);
}
