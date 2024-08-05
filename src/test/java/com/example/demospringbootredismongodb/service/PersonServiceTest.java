package com.example.demospringbootredismongodb.service;

import com.example.demospringbootredismongodb.entity.Person;
import com.example.demospringbootredismongodb.repository.PersonRepository;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPersonById() {
        Person person = new Person("1", "John Doe", 30);
        when(personRepository.findById("1")).thenReturn(Optional.of(person));

        Person result = personService.getPersonById("1");
        assertEquals("John Doe", result.getName());
        verify(personRepository, times(1)).findById("1");
    }

    @Test
    public void testCreatePerson() {
        Person person = new Person("1", "John Doe", 30);
        when(personRepository.save(any(Person.class))).thenReturn(person);

        Person result = personService.createPerson(person);
        assertEquals("John Doe", result.getName());
        verify(personRepository, times(1)).save(person);
        verify(redisTemplate, times(1)).convertAndSend(eq("personCreated"), any(Person.class));
    }
}
