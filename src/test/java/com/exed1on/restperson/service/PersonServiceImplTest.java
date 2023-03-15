package com.exed1on.restperson.service;

import com.exed1on.restperson.entity.Person;
import com.exed1on.restperson.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonService personService;
    @Test
    public void givenBirthDate_whenGetAge_thenReturnAge(){
        LocalDate date = LocalDate.parse("1992-04-05");
        int calculatedAge = 30;

        int age = personService.getAge(date);

        assertThat(age).isEqualTo(calculatedAge);
    }

    @Test
    public void givenPerson_whenGetPerson_thenReturnNameSurnameAge(){
        Person person = new Person(1L, "Max", "Xam", LocalDate.parse("1992-04-05"));
        personRepository.save(person);
        String returnedObjected = "PersonDto(name=Max, surname=Xam, age=30)";
        given(personRepository.findById(1L)).willReturn(Optional.of(person));

        String object = String.valueOf(personService.getPerson(1));

        assertThat(object).isEqualTo(returnedObjected);
    }
}