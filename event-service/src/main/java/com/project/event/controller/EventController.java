package com.project.event.controller;

import com.project.event.dto.get.GetFullEventDto;
import com.project.event.dto.post.PostFullEventDto;
import com.project.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @GetMapping
    public List<GetFullEventDto> getAllDtos() {
        rabbitTemplate.convertAndSend(exchange,routingKey,"event with  has been deleted");
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    public GetFullEventDto getById(@PathVariable long id) {
        return eventService.getById(id);
    }

    @PostMapping
    public GetFullEventDto addEvent(@RequestBody PostFullEventDto postFullEventDto) {
        rabbitTemplate.convertAndSend(exchange, routingKey, "event with  has been deleted");
        return eventService.addEvent(postFullEventDto);
    }


    @PutMapping("/{id}")
    public GetFullEventDto updateEventById(@PathVariable long id, @RequestBody PostFullEventDto postFullEventDto) {
        return eventService.updateById(id, postFullEventDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        eventService.deleteById(id);
        rabbitTemplate.convertAndSend(exchange, routingKey, "event with id: " + id + " has been deleted");
    }


}
