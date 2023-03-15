package com.exed1on.restperson.controller;

import com.exed1on.restperson.RestpersonApplication;
import com.exed1on.restperson.dto.PersonDto;
import com.exed1on.restperson.entity.Person;
import com.exed1on.restperson.repository.PersonRepository;
import com.exed1on.restperson.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = PersonRestController.class)
class PersonRestControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenPeople_whenGetPerson_thenReturnNameSurnameAge() throws Exception {
        when(personService.getPerson(anyLong())).thenReturn(PersonDto.builder()
                .name("Max")
                .surname("Xam")
                .age(25)
                .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/person/get/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Max"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Xam"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(25));
    }
}