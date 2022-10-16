package com.trepudox.springrabbitconsumer.domain.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Person {

    @Id
    private Long id;
    private String name;
    private int age;

}
