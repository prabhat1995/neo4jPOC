package com.example.neo4jdemo.service;

import com.example.neo4jdemo.entity.Person;
import com.example.neo4jdemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person savePerson(Person person){
        return personRepository.save(person);
    }

    public Person getPersonByName(String name){
        return personRepository.findByName(name);
    }


}
