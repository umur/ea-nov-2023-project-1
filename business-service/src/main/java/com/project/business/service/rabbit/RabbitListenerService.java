package com.project.business.service.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.business.dto.put.PutProfExperienceDto;
import com.project.business.model.ProfExperience;
import com.project.business.repository.ProfExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitListenerService {
    private final ModelMapper modelMapper;
    private final ProfExperienceRepository profExperienceRepository;

    @RabbitListener(queues = {"q2"})
    public void createOrUpdateProfExperience(@Payload String jsonProfExperience) {
        try {
            var data = modelMapper.map(new ObjectMapper().readValue(jsonProfExperience, PutProfExperienceDto.class), ProfExperience.class);
            profExperienceRepository.save(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @RabbitListener(queues = {"q3"})
    public void deleteProfExperience(@Payload String alumniId) {
        var alumniIdLong = Long.parseLong(alumniId);
        profExperienceRepository.deleteAllByAlumniId(alumniIdLong);
    }
}
