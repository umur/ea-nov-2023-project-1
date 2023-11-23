package edu.ea.userservice.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    @Value("${student.queue}")
    private  String studentQueueName;

    @Value("${job.queue}")
    private  String jobQueueName;
    @Value("${alumni.rabbitmq.exchange}")
    private  String exchange;

    @Value("${student.routingkey}")
    private String studentRoutingkey;
    @Value("${job.routingkey}")
    private String jobRoutingkey;

    private final AmqpAdmin amqpAdmin;
    @PostConstruct
    public void createQueues() {
        amqpAdmin.declareQueue(new Queue(studentQueueName, true));
        amqpAdmin.declareQueue(new Queue(jobQueueName, true));
        amqpAdmin.declareExchange(new DirectExchange(exchange));
    }
    @Bean
    Queue studentQueue() {
        return new Queue(studentQueueName, true);
    }

    @Bean
    Queue jobQueue() {
        return new Queue(jobQueueName, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }



    @Bean
    Binding studentBinding(Queue studentQueue, DirectExchange exchange) {
        return BindingBuilder.bind(studentQueue).to(exchange).with(studentRoutingkey);
    }

    @Bean
    Binding jobBinding(Queue jobQueue, DirectExchange exchange) {
        return BindingBuilder.bind(jobQueue).to(exchange).with(jobRoutingkey);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate amqRabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
      rabbitTemplate.setMessageConverter(jsonMessageConverter());

        return rabbitTemplate;
    }
}
