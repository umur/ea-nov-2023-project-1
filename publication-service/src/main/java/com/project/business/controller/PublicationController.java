package com.project.business.controller;

import com.project.business.model.Publication;
import com.project.business.model.type.PublicationType;
import com.project.business.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publications")
public class PublicationController {
    private final PublicationService publicationService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @GetMapping("/type")
    public List<Publication> findAllByPublicationType(@RequestParam PublicationType type) {
        return publicationService.findAllByPublicationType(type);
    }

    @GetMapping("/{id}")
    public Publication getById(@PathVariable long id) {
        return publicationService.getById(id);
    }

    @PostMapping()
    public Publication addPublication(@RequestBody Publication publication) {
        rabbitTemplate.convertAndSend("exchange1", "routing-key", "Bacha");
        return publicationService.addPublication(publication);
    }

    @PutMapping("/{id}")
    public Publication updateById(@PathVariable long id, @RequestBody Publication publication) {
        return publicationService.updateById(id, publication);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id){
        publicationService.deleteById(id);
        rabbitTemplate.convertAndSend(exchange,routingKey, "publication with id: " + id + " has been deleted");
    }


}
