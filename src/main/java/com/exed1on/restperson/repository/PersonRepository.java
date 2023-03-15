package com.exed1on.restperson.repository;

import com.exed1on.restperson.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
