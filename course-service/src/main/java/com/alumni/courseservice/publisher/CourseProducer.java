package com.alumni.courseservice.publisher;

import com.alumni.courseservice.payload.CourseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CourseProducer {

    private final Logger LOGGER = LoggerFactory.getLogger(CourseProducer.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.binding.routing.key}")
    private String orderRoutingKey;


    private final RabbitTemplate rabbitTemplate;

    public CourseProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(CourseStatus courseEvent){
        LOGGER.info(String.format("Course event sent to RabbitMQ => %s", courseEvent.toString()));

        // send a course event to user queue
        rabbitTemplate.convertAndSend(exchange, orderRoutingKey, courseEvent);

    }
}
