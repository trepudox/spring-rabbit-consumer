package com.trepudox.springrabbitconsumer.domain.repository;

import com.trepudox.springrabbitconsumer.domain.entity.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, ObjectId> {
    // atualmente quebrando a arquitetura por causa do extends, acoplando o dominio com o MongoDB
}
