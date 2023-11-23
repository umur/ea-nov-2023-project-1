package com.project.education.service.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.education.model.EduExperience;
import com.project.education.repository.EduExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitListenerService {
    private final ModelMapper modelMapper;
    private final EduExperienceRepository eduExperienceRepository;

    @RabbitListener(queues = {"q4"})
    public void createOrUpdateEduExperience(@Payload String jsonEduExperience) {
//        try {
//            var data = modelMapper.map(new ObjectMapper().readValue(jsonEduExperience, PutEduExperienceDto.class), EduExperience.class);
//            EduExperienceRepository.save(data);

//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
            return;
    }
    @RabbitListener(queues = {"q3"})
    public void deleteEduExperience(@Payload String alumniId) {
        var alumniIdLong = Long.parseLong(alumniId);
//        EduExperienceRepository.deleteAllByAlumniId(alumniIdLong);
    }
}
