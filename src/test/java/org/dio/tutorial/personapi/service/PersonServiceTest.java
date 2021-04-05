package org.dio.tutorial.personapi.service;

import org.dio.tutorial.personapi.dto.request.PersonDTO;
import org.dio.tutorial.personapi.dto.response.MessageResponseDTO;
import org.dio.tutorial.personapi.entity.Person;
import org.dio.tutorial.personapi.repository.PersonRepository;
import org.dio.tutorial.personapi.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSaveMessage() {
        final PersonDTO personDTO = PersonUtils.createFakeDTO();
        final Person expectedSavedPerson = PersonUtils.createFakeEntity();

        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(expectedSavedPerson);

        final MessageResponseDTO expectedMessage =
                MessageResponseDTO.of("Created personDTO with ID " + expectedSavedPerson.getId());
        final MessageResponseDTO responseMessage = personService.createPerson(personDTO);

        Assertions.assertEquals(expectedMessage, responseMessage);
    }
}
