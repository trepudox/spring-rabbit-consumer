package com.trepudox.springrabbitconsumer.application.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trepudox.springrabbitconsumer.application.request.PersonRequest;
import com.trepudox.springrabbitconsumer.application.response.PersonResponse;
import com.trepudox.springrabbitconsumer.domain.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonResponse personToPersonResponse(Person person);

    default String personToJson(PersonRequest personRequest) {
        try {
            return new ObjectMapper().writeValueAsString(personRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    default PersonRequest jsonToPerson(String json) {
        try {
            return new ObjectMapper().readValue(json, PersonRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
