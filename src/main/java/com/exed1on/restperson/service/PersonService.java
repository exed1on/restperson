package com.exed1on.restperson.service;

import com.exed1on.restperson.dto.PersonDto;
import com.exed1on.restperson.entity.Person;
import com.exed1on.restperson.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public int getAge(LocalDate localDate){
        return (int) ChronoUnit.YEARS.between(localDate, LocalDate.now());
    }
    public PersonDto getPerson(long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.map(value -> PersonDto.builder()
                .name(value.getName())
                .surname(value.getSurname())
                .age(getAge(value.getBirthDay()))
                .build()).orElse(null);
    }
}
