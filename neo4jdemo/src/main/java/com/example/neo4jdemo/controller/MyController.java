package com.example.neo4jdemo.controller;

import com.example.neo4jdemo.entity.Group;
import com.example.neo4jdemo.entity.Person;
import com.example.neo4jdemo.service.GroupService;
import com.example.neo4jdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MyController {

    @Autowired
    private PersonService personService;

    @Autowired
    private GroupService groupService;

    public Person savePerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    public Group savePerson(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }
}
