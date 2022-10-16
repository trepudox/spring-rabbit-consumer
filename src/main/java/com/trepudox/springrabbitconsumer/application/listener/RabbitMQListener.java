package com.trepudox.springrabbitconsumer.application.listener;

import com.trepudox.springrabbitconsumer.application.mapper.PersonMapper;
import com.trepudox.springrabbitconsumer.application.request.PersonRequest;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class RabbitMQListener {

    @RabbitListener(queues = {"${spring.rabbitmq.queue}"})
    public void listenToQueue(Message message) {
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        System.out.println(body);
        PersonRequest personRequest = PersonMapper.INSTANCE.jsonToPerson(body);
        System.out.println(personRequest);
    }

}
