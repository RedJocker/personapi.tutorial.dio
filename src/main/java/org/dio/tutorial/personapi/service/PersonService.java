package org.dio.tutorial.personapi.service;

import lombok.RequiredArgsConstructor;
import org.dio.tutorial.personapi.dto.MessageResponseDTO;
import org.dio.tutorial.personapi.entity.Person;
import org.dio.tutorial.personapi.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class PersonService {


    private final PersonRepository personRepository;

    public MessageResponseDTO createPerson(@RequestBody Person person){
        final Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }
}
