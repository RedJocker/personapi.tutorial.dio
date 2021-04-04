package org.dio.tutorial.personapi.controller;

import lombok.RequiredArgsConstructor;

import org.dio.tutorial.personapi.dto.MessageResponseDTO;
import org.dio.tutorial.personapi.entity.Person;
import org.dio.tutorial.personapi.service.PersonService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private final PersonService personService;

    @GetMapping()
    public String test() {
        return "API test";
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person) {

        return personService.createPerson(person);
    }

}
