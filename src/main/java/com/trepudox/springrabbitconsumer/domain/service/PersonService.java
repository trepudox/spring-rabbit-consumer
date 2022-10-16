package com.trepudox.springrabbitconsumer.domain.service;

import com.trepudox.springrabbitconsumer.domain.entity.Person;
import com.trepudox.springrabbitconsumer.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Mono<Person> getById(Long id) {
        return personRepository.findById(id);
    }

    public Flux<Person> getAll() {
        return personRepository.findAll();
    }
}
