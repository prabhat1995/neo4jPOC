package com.medium.accessmanagement.controller;

import com.medium.accessmanagement.entity.*;
import com.medium.accessmanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    PersonService personService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    RoleService roleService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    MemberService memberService;

    @Autowired
    AccessService accessService;

    @Autowired
    RoleGroupService roleGroupService;

    @Autowired
    HasGroupService hasGroupService;

    @Autowired
    HasRoleService hasRoleService;

    @Autowired
    RoleAccessService roleAccessService;

    @PostMapping("/persons")
    public Person savePerson(@RequestBody Person person){
        return personService.savePerson(person);
    }

    @PostMapping("/organizations")
    public Organization saveOrganization(@RequestBody Organization organization){
        return organizationService.saveOrganization(organization);
    }

    @PutMapping("/organizations")
    public Organization updateOrganization(@RequestBody InputRelationship body){
        return organizationService.updateOrganization(body);
    }

    @PostMapping("/roles")
    public HasRole saveRole(@RequestBody InputRelationship body){
        return roleService.saveRole(body);
    }

    @PostMapping("/resources")
    public Resource saveGroup(@RequestBody Resource resource){
        return resourceService.saveResource(resource);
    }

    @PostMapping("/rolegroups")
    public RoleGroup saveRoleGroup(@RequestBody RoleGroup roleGroup){
        return roleGroupService.saveRoleGroup(roleGroup);
    }

    @PostMapping("/relationship/belongsto")
    public Member createRelationshipBelongsTo(@RequestBody InputRelationship body){
        return memberService.createRelationship(body);
    }

    @PutMapping("/relationship/belongsto")
    public Member updateRelationshipBelongsTo(@RequestBody InputRelationship body){
        return memberService.updateRelationship(body);
    }

    @DeleteMapping("/relationship/organizations/{orgId}/persons/{personId}")
    public void updateRelationshipBelongsTo(@PathVariable("orgId") String orgId, @PathVariable("personId") String personId){
        memberService.deleteMember(orgId, personId);
        return;
    }

    @PostMapping("/relationship/hasgroup")
    public HasGroup createRelationshipHasGroup(@RequestBody InputRelationship body){
        return hasGroupService.createRelationship(body);
    }

    @PostMapping("/relationship/hasrole")
    public HasRole createRelationshipHasRole(@RequestBody InputRelationship body){
        return hasRoleService.createRelationShip(body);
    }

    @PostMapping("/relationship/roleaccess")
    public RoleAccess createRelationshipForRoleAccess(@RequestBody InputRelationship body){
        return roleAccessService.createRelationship(body);
    }

    @PutMapping("/relationship/roleaccess")
    public RoleAccess updateRelationshipForRoleAccess(@RequestBody InputRelationship body){
        return roleAccessService.updateRelationshipForRoleAccess(body);
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
