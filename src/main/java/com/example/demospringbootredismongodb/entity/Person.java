package com.example.demospringbootredismongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Document(collection = "persons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    private String id;
    private String name;
    private int age;
}