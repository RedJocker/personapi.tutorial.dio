package org.dio.tutorial.personapi.repository;

import org.dio.tutorial.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
