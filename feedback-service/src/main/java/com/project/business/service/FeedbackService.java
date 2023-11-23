package com.project.business.service;

import com.project.business.dto.get.GetAFullFeedbackDto;
import com.project.business.dto.post.PostFullFeedbackDto;

import java.util.List;

public interface FeedbackService {
    List<GetAFullFeedbackDto> getAllFeedBacks();
    GetAFullFeedbackDto getFeedBackById(Long id);
    GetAFullFeedbackDto saveFeedback(PostFullFeedbackDto postFullFeedbackDto);
    GetAFullFeedbackDto updateFeedback(Long feedbackId, PostFullFeedbackDto postFullFeedbackDto);
    void deleteFeedback(Long feedbackId);
    List<GetAFullFeedbackDto> findByAlumniId(Long alumniId);
}
