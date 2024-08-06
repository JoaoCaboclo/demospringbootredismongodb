package com.example.demospringbootredismongodb;

import com.example.demospringbootredismongodb.entity.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemoSpringBootRedisComMongodbApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoSpringBootRedisComMongodbApplication.class, args);

		Person person = Person.builder()
				.id("123")
				.name("John Doe")
				.age(30)
				.build();
	}

}
