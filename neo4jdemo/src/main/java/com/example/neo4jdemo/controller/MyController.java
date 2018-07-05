package com.example.neo4jdemo.controller;

import com.example.neo4jdemo.entity.*;
import com.example.neo4jdemo.repository.AccessRepository;
import com.example.neo4jdemo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class MyController {

    @Autowired
    private PersonService personService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AccessService accessService;

    @Autowired
    private MemberService memberService;

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

    @RequestMapping(value="/groups/members",method = RequestMethod.GET)
    public List<Map<String,Object>> getGroupWithMemberDetails() {
        return groupService.getGroupWithMembers();
    }

    //create  has access relationship
    @RequestMapping(value="/relationship/hasaccess",method = RequestMethod.POST)
    public Access createRelationshipHasAccess(@RequestBody InputRelationship body) {

        return accessService.createRelationship(body);
    }

    //create belongs to relationship
    @RequestMapping(value="/relationship/belongsto",method = RequestMethod.POST)
    public Member createRelationshipBelongsTo(@RequestBody InputRelationship body) {

        return memberService.createRelation(body.getPersonName(),body.getGroupName());
    }

    //access management
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public Boolean checkAccessToResource(@RequestBody InputRelationship body){
                return groupService.checkPersonAccess(body.getPersonName(),body.getRoute());
    }
}
