package com.trepudox.springrabbitconsumer.domain.service;

import com.trepudox.springrabbitconsumer.application.mapper.PersonMapper;
import com.trepudox.springrabbitconsumer.application.request.PersonRequest;
import com.trepudox.springrabbitconsumer.domain.entity.Person;
import com.trepudox.springrabbitconsumer.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Mono<Person> getById(String objectIdString) {
        try {
            return personRepository.findById(new ObjectId(objectIdString));
        } catch(IllegalArgumentException e) {
            return Mono.empty();
        }
    }

    public Flux<Person> getAll() {
        return personRepository.findAll();
    }

    public void save(PersonRequest personRequest) {
        Mono.just(personRequest)
                .log()
                .map(PersonMapper.INSTANCE::personRequestToPerson)
                .map(p -> {
                    p.setId(ObjectId.get());
                    System.out.println(p);
                    return p;
                })
                .subscribe(p -> personRepository.save(p).subscribe(), RuntimeException::new);
    }
}
