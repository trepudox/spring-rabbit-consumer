package com.trepudox.springrabbitconsumer.application.controller;

import com.trepudox.springrabbitconsumer.application.mapper.PersonMapper;
import com.trepudox.springrabbitconsumer.application.response.PersonResponse;
import com.trepudox.springrabbitconsumer.domain.entity.Person;
import com.trepudox.springrabbitconsumer.domain.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        Flux<Person> personFlux = personService.getAll();
        Flux<PersonResponse> response = personFlux.map(PersonMapper.INSTANCE::personToPersonResponse);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{objectId}")
    public ResponseEntity<?> getById(@PathVariable String objectId) {
        Mono<Person> personMono = personService.getById(objectId);
        Mono<PersonResponse> response = personMono.map(PersonMapper.INSTANCE::personToPersonResponse);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
