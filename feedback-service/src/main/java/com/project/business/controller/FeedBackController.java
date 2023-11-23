package com.project.business.controller;

import com.project.business.dto.get.GetAFullFeedbackDto;
import com.project.business.dto.post.PostFullFeedbackDto;
import com.project.business.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
public class FeedBackController {
    private final FeedbackService feedbackService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;


    @GetMapping
    public List<GetAFullFeedbackDto> getAllFeedbacks(){
        return feedbackService.getAllFeedBacks();
    }
    @GetMapping("/alumni-id")
    public List<GetAFullFeedbackDto> findByAlumniId(@RequestParam Long id){
        return feedbackService.findByAlumniId(id);
    }
    @PostMapping("/save")
    public GetAFullFeedbackDto saveFeedback(PostFullFeedbackDto postFullFeedbackDto) {
        return feedbackService.saveFeedback(postFullFeedbackDto);
    }

    @PutMapping("/{id}")
      public GetAFullFeedbackDto updateFeedback(@PathVariable Long id, @RequestBody PostFullFeedbackDto updatedPostFullFeedbackDto){
        return feedbackService.updateFeedback(id,updatedPostFullFeedbackDto);
    }
    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        rabbitTemplate.convertAndSend(exchange,routingKey, "Feedback with id: " + id + " has been deleted");
    }

}
