package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.InputRelationship;
import com.medium.accessmanagement.entity.Person;
import com.medium.accessmanagement.entity.Role;
import com.medium.accessmanagement.entity.RoleAccess;
import com.medium.accessmanagement.repository.PersonRepository;
import com.medium.accessmanagement.repository.RoleAccessRepository;
import com.medium.accessmanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleAccessService {

    @Autowired
    RoleAccessRepository roleAccessRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RoleRepository roleRepository;

//    public RoleAccess createRelationship(InputRelationship body){
//        Person person = personRepository.findByPersonId(body.getPersonId());
//        Role role = roleRepository.findByName(body.getRole());
//
//        Set<String> organizationIds = new HashSet<>();
//        organizationIds.add(body.getOrganizationId());
//
//        RoleAccess relationship = new RoleAccess();
//        relationship.setPerson(person);
//        relationship.setRole(role);
//        relationship.setOrganizationIds(organizationIds);
//
//        return roleAccessRepository.save(relationship);
//    }
    public RoleAccess createRelationship(InputRelationship body){
        Person person = personRepository.findByPersonId(body.getPersonId());
        Role role = roleRepository.findByName(body.getRole());
        Set<String> organizationIds;
        RoleAccess relationship = roleAccessRepository.findOrganizationIdsForPersonRole(body.getRole(), body.getPersonId(), body.getOrganizationId(), body.getRoleGroupName());
        if(relationship != null) {
            organizationIds = relationship.getOrganizationIds();
            organizationIds.add(body.getOrganizationId());
        }else{
            relationship = new RoleAccess();
            organizationIds = new HashSet<>();
            organizationIds.add(body.getOrganizationId());
        }

        relationship.setPerson(person);
        relationship.setRole(role);
        relationship.setOrganizationIds(organizationIds);
        return roleAccessRepository.save(relationship);
    }
}
