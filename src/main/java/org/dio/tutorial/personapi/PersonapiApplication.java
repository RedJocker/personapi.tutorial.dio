package org.dio.tutorial.personapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class PersonapiApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(PersonapiApplication.class, args);
	}
}
