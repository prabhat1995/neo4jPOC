package com.example.neo4jdemo.controller;

import com.example.neo4jdemo.entity.*;
import com.example.neo4jdemo.repository.AccessRepository;
import com.example.neo4jdemo.service.AccessService;
import com.example.neo4jdemo.service.GroupService;
import com.example.neo4jdemo.service.PersonService;
import com.example.neo4jdemo.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
//@RequestMapping("/")
public class MyController {

    @Autowired
    private PersonService personService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AccessService accessService;

    //save person/user
    @RequestMapping(value="/person",method = RequestMethod.POST)
    public Person savePerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    //save group
    @RequestMapping(value="/group",method = RequestMethod.POST)
    public Group saveGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }

    //save resource
    @RequestMapping(value="/resource",method = RequestMethod.POST)
    public Resource saveResource(@RequestBody Resource resource) {
        return resourceService.saveResource(resource);
    }

    @RequestMapping(value="/groups",method = RequestMethod.GET)
    public List<Group> getGroups() {
        return groupService.getAllGroups();
    }


    @RequestMapping(value="/groups/{title}",method = RequestMethod.GET)
    public Collection<Group> getGroupByName(@PathVariable("title") String title) {
        return groupService.getGroupDetails(title);
    }


    @RequestMapping(value="/resource/{route}",method = RequestMethod.GET)
    public Collection<Resource> getResourceByRoute(@PathVariable("route") String route) {
        return resourceService.getResourceDetail(route);
    }


    //create relationship
    @RequestMapping(value="/relationship",method = RequestMethod.POST)
    public Access createRelationship(@RequestBody InputRelationship body) {

        return accessService.createRelationship(body);
    }


}
