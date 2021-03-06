package org.dio.tutorial.personapi.mapper;

import org.dio.tutorial.personapi.dto.request.PersonDTO;
import org.dio.tutorial.personapi.entity.Person;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    @InheritInverseConfiguration
    PersonDTO toDTO(Person person);
}
