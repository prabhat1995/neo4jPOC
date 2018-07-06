package com.medium.accessmanagement.controller;

import com.medium.accessmanagement.entity.InputRelationship;
import com.medium.accessmanagement.entity.Member;
import com.medium.accessmanagement.entity.Organization;
import com.medium.accessmanagement.entity.Person;
import com.medium.accessmanagement.service.MemberService;
import com.medium.accessmanagement.service.OrganizationService;
import com.medium.accessmanagement.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    PersonService personService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    MemberService memberService;

    @PostMapping("/persons")
    public Person savePerson(@RequestBody Person person){
        return personService.savePerson(person);
    }

    @PostMapping("/organizations")
    public Organization saveOrganization(@RequestBody Organization organization){
        return organizationService.saveOrganization(organization);
    }

    @PostMapping("/relationship/belongsto")
    public Member createRelationshipBelongsTo(@RequestBody InputRelationship body){
        return memberService.createRelationship(body);
    }
}
