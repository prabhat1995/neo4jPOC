package com.medium.accessmanagement.controller;

import com.medium.accessmanagement.entity.*;
import com.medium.accessmanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    PersonService personService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    GroupService groupService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    MemberService memberService;

    @Autowired
    AccessService accessService;

    @PostMapping("/persons")
    public Person savePerson(@RequestBody Person person){
        return personService.savePerson(person);
    }

    @PostMapping("/organizations")
    public Organization saveOrganization(@RequestBody Organization organization){
        return organizationService.saveOrganization(organization);
    }

    @PostMapping("/groups")
    public Group saveGroup(@RequestBody Group group){
        return groupService.saveGroup(group);
    }

    @PostMapping("/resources")
    public Resource saveGroup(@RequestBody Resource resource){
        return resourceService.saveResource(resource);
    }

    @PostMapping("/relationship/belongsto")
    public Member createRelationshipBelongsTo(@RequestBody InputRelationship body){
        return memberService.createRelationship(body);
    }

    @PostMapping("/relationship/hasaccess")
    public Access createRelationshipHasAccess(@RequestBody InputRelationship body){
        return accessService.createAccess(body);
    }

    @PostMapping("/relationship/check")
    public Boolean checkAccessToResource(@RequestBody InputRelationship body){
        return resourceService.checkPersonAccess(body);
    }
}
