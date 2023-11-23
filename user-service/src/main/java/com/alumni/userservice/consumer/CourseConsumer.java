package com.alumni.userservice.consumer;

import com.alumni.userservice.payload.CourseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CourseConsumer {

    private Logger LOGGER = LoggerFactory.getLogger(CourseConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.course.name}")
    public void consume(CourseStatus event){
        LOGGER.info(String.format("Course event received => %s", event.toString()));

        /* save Course event data in database */
    }
}
