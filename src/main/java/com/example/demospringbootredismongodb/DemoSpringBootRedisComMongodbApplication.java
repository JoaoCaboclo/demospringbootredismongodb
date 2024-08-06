package com.example.demospringbootredismongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemoSpringBootRedisComMongodbApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoSpringBootRedisComMongodbApplication.class, args);

	}

}
