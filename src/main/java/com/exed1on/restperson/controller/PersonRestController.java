package com.exed1on.restperson.controller;

import com.exed1on.restperson.dto.PersonDto;
import com.exed1on.restperson.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/person")
public class PersonRestController {
    private final PersonService personService;
    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping("/get/{id}")
    public PersonDto getPerson(@PathVariable long id){
       return personService.getPerson(id);
    }
}
