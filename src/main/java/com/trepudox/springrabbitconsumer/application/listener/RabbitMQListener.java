package com.trepudox.springrabbitconsumer.application.listener;

import com.trepudox.springrabbitconsumer.application.mapper.PersonMapper;
import com.trepudox.springrabbitconsumer.domain.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final PersonService personService;

    @RabbitListener(queues = {"${spring.rabbitmq.queue}"})
    public void listenToQueue(Message message) {
        Mono.just(message)
            .log()
            .map(msg -> new String(message.getBody(), StandardCharsets.UTF_8))
            .map(PersonMapper.INSTANCE::jsonToPersonRequest)
            .subscribe(personService::save, RuntimeException::new);
    }

}
