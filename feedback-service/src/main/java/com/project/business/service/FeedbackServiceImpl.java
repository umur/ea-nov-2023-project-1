package com.project.business.service;


import com.project.business.dto.get.GetAFullFeedbackDto;
import com.project.business.dto.post.PostFullFeedbackDto;
import com.project.business.model.Feedback;
import com.project.business.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService{
    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<GetAFullFeedbackDto> getAllFeedBacks() {
        var result = feedbackRepository.findAll();
        List<GetAFullFeedbackDto> dtoList = new ArrayList<>();
        result.forEach(feedback -> dtoList.add(modelMapper.map(feedback, GetAFullFeedbackDto.class)));
        return dtoList ;
    }

    @Override
    public GetAFullFeedbackDto getFeedBackById(Long id) {
        var result = feedbackRepository.findById(id);
        return modelMapper.map(result, GetAFullFeedbackDto.class);
    }

    @Override
    public GetAFullFeedbackDto saveFeedback(PostFullFeedbackDto postFullFeedbackDto) {
        var result = feedbackRepository.save(modelMapper.map(postFullFeedbackDto, Feedback.class));
        return modelMapper.map(result, GetAFullFeedbackDto.class);
    }

    @Override
    public GetAFullFeedbackDto updateFeedback(Long feedbackId, PostFullFeedbackDto postFullFeedbackDto) {
        var feedBack = modelMapper.map(postFullFeedbackDto, Feedback.class);
        feedBack.setId(feedbackId);
        var result = feedbackRepository.save(feedBack);
        return modelMapper.map(result, GetAFullFeedbackDto.class);
    }

    @Override
    public void deleteFeedback(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    @Override
    public List<GetAFullFeedbackDto> findByAlumniId(Long alumniId) {
        var result = feedbackRepository.findByAlumniId(alumniId);
        List<GetAFullFeedbackDto> dtoList = new ArrayList<>();
        result.forEach(feedback -> dtoList.add(modelMapper.map(feedback, GetAFullFeedbackDto.class)));
        return dtoList ;
    }
}

