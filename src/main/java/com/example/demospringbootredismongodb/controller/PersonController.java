package com.example.demospringbootredismongodb.controller;

import com.example.demospringbootredismongodb.entity.Person;
import com.example.demospringbootredismongodb.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
@NoArgsConstructor
@AllArgsConstructor
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable String id) {
        return personService.getPersonById(id);
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }
}