package com.project.alumni.service.Impl;

import com.project.alumni.dto.InsightsDto;
import com.project.alumni.entity.Insights;
import com.project.alumni.repository.AlumniInsightsRepo;
import com.project.alumni.service.InsightsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InsightsServiceImpl implements InsightsService {
    private final AlumniInsightsRepo alumniInsightsRepo;
    private final ModelMapper modelMapper;
    @Override
    public void save(InsightsDto insightsDto) {
        alumniInsightsRepo.save(modelMapper.map(insightsDto, Insights.class));
    }

    @Override
    public List<InsightsDto> findAll() {
        List<Insights> insights = alumniInsightsRepo.findAll();
        var res = new ArrayList<InsightsDto>();
        insights.forEach(e -> {
            if (e.getDeleted() == 0){
                res.add(modelMapper.map(e, InsightsDto.class));
            }
        });
        return res;
    }

    @Override
    public InsightsDto findById(Long id) {
        var res = alumniInsightsRepo.findById(id);
        if (!res.isPresent() || res.get().getDeleted() == 1){
            return null;
        }
        return modelMapper.map(res, InsightsDto.class);
    }

    @Override
    public void update(Long id, InsightsDto insightsDto) {
        var alumniInsights = alumniInsightsRepo.findById(id);
        if (alumniInsights.isPresent() && alumniInsights.get().getDeleted() == 0){
            Insights var1 = modelMapper.map(insightsDto, Insights.class);
            var1.setId(id);
            var1.setTitle(insightsDto.getTitle());
            var1.setDescription(insightsDto.getDescription());
            var1.setPublicationDate(insightsDto.getPublicationDate());
            alumniInsightsRepo.save(var1);
        }
    }

    @Override
    public void delete(Long id) {
        var alumniInsight = alumniInsightsRepo.findById(id);
        if (alumniInsight.isPresent()){
            alumniInsight.get().setDeleted(1);
        }
    }
}
