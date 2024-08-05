package com.example.demospringbootredismongodb.service;

import com.example.demospringbootredismongodb.entity.Person;
import com.example.demospringbootredismongodb.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Cacheable(value = "persons", key = "#id")
    public Person getPersonById(String id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person createPerson(Person person) {
        Person savedPerson = personRepository.save(person);
        redisTemplate.convertAndSend("personCreated", savedPerson);
        return savedPerson;
    }
}