package com.example.neo4jdemo.controller;

import com.example.neo4jdemo.entity.Group;
import com.example.neo4jdemo.entity.Person;
import com.example.neo4jdemo.service.GroupService;
import com.example.neo4jdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/")
public class MyController {

    @Autowired
    private PersonService personService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value="/person",method = RequestMethod.POST)
    public Person savePerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @RequestMapping(value="/group",method = RequestMethod.POST)
    public Group saveGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }

    @RequestMapping(value="/groups",method = RequestMethod.GET)
    public List<Group> getGroups() {
        return groupService.getAllGroups();
    }
}
