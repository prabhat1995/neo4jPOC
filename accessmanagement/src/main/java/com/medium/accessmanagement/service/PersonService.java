package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.Person;
import com.medium.accessmanagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person savePerson (Person person) {
        return personRepository.save(person);
    }
}
