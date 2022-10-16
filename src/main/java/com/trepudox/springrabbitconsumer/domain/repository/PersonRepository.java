package com.trepudox.springrabbitconsumer.domain.repository;

import com.trepudox.springrabbitconsumer.domain.entity.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {
}
