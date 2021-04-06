package org.dio.tutorial.personapi.service;

import org.dio.tutorial.personapi.dto.request.PersonDTO;
import org.dio.tutorial.personapi.dto.response.MessageResponseDTO;
import org.dio.tutorial.personapi.entity.Person;
import org.dio.tutorial.personapi.exception.PersonNotFoundException;
import org.dio.tutorial.personapi.mapper.PersonMapper;
import org.dio.tutorial.personapi.repository.PersonRepository;
import org.dio.tutorial.personapi.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    private PersonMapper mapper = PersonMapper.INSTANCE;


    @Nested
    class CreatePersonTest {
        @Test
        @DisplayName("createPerson() when given PersonDTO return saved message")
        void testCreatePersonGivenPersonDTOThenReturnSaveMessage() {
            final PersonDTO personDTO = PersonUtils.createFakeDTO();
            final Person expectedSavedPerson = PersonUtils.createFakeEntity();

            Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(expectedSavedPerson);

            final MessageResponseDTO expectedMessage =
                    MessageResponseDTO.of("Created personDTO with ID " + expectedSavedPerson.getId());
            final MessageResponseDTO responseMessage = personService.createPerson(personDTO);

            Assertions.assertEquals(expectedMessage, responseMessage);
        }

        @Test
        @DisplayName("createPerson() when broken constraint return error")
        void testCreatePersonGivenBrokenConstraintThenReturnError() {
            final PersonDTO personDTO = PersonUtils.createFakeDTO();

            Mockito.when(personRepository.save(Mockito.any(Person.class)))
                    .thenThrow(DataIntegrityViolationException.class);

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> personService.createPerson(personDTO));
        }
    }



    @Nested
    class ListAllTest {


        @Test
        @DisplayName("listAll() when not empty return list of personDTO")
        void testListAllWhenNotEmptyReturnListDTO() {
            final List<Person> stubList = PersonUtils.getFakeRepository();

            Mockito.when(personRepository.findAll()).thenReturn(stubList);

            final List<PersonDTO> expected = stubList.stream().map(mapper::toDTO).collect(Collectors.toList());
            final List<PersonDTO> response = personService.listAll();

            Assertions.assertEquals(expected, response);

        }


        @Test
        @DisplayName("listAll() when empty return empty list")
        void testListAllWhenEmptyReturnEmptyList() {
            final List<Person> stubList = List.of();

            Mockito.when(personRepository.findAll()).thenReturn(stubList);

            final List<PersonDTO> expected = List.of();
            final List<PersonDTO> response = personService.listAll();

            Assertions.assertEquals(expected, response);

        }
    }

    @Nested
    class FindByIdTest {

        @Test
        @DisplayName("findById() when found id return personDTO with id")
        void testFindByIdWhenFoundReturnPersonDto() {
            final Person fakeEntity = PersonUtils.createFakeEntity();

            Mockito.when(personRepository.findById(fakeEntity.getId()))
                    .thenReturn(Optional.of(fakeEntity));

            final PersonDTO expected = mapper.toDTO(fakeEntity);
            final PersonDTO result = personService.findById(fakeEntity.getId());

            Assertions.assertEquals(expected, result);
        }

        @Test
        @DisplayName("findById() when not found id return PersonNotFoundException")
        void testFindByIdWhenNotFoundReturnPersonNotFoundException() {

            Mockito.when(personRepository.findById(1L))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(PersonNotFoundException.class, () -> personService.findById(1L));

            try {
                personRepository.findById(1L);
            } catch (PersonNotFoundException e) {
                final String expectedMessage = "Person not found with id " + 1L;
                Assertions.assertEquals(expectedMessage, e.getMessage());
            }
        }
    }

}
