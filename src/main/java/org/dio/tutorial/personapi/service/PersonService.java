package org.dio.tutorial.personapi.service;

import lombok.RequiredArgsConstructor;
import org.dio.tutorial.personapi.dto.request.PersonDTO;
import org.dio.tutorial.personapi.dto.response.MessageResponseDTO;
import org.dio.tutorial.personapi.entity.Person;
import org.dio.tutorial.personapi.exception.PersonNotFoundException;
import org.dio.tutorial.personapi.mapper.PersonMapper;
import org.dio.tutorial.personapi.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {


    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO){

        final Person personToSave = personMapper.toModel(personDTO);
        System.out.println(personToSave);
        final Person savedPerson = personRepository.save(personToSave);

        return MessageResponseDTO.of("Created personDTO with ID " + savedPerson.getId());
    }

    public List<PersonDTO> listAll() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) {
        return personRepository.findById(id)
                .map(personMapper::toDTO)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    public void deleteById(Long id) {
        personRepository.findById(id)
                .stream()
                .peek(personRepository::delete)
                .findFirst()
                .orElseThrow(() -> new PersonNotFoundException(id));

    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) { // !! a atual implementação não está reutilizando ids dos telefones no update, ao menos que os mesmos sejam referenciados corretamente no json da request, gerando assim números duplicados na tabela de telefone que não são referenciados por pessoa nenhuma.
        personDTO.setId(id);
        final Person personToUpdate = personMapper.toModel(personDTO);

        return personRepository.findById(id)
                .stream()
                .map(found -> personRepository.save(personToUpdate))
                .map(person -> MessageResponseDTO.of("Updated personDTO with ID " + person.getId()))
                .findFirst()
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
}
