package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.Access;
import com.medium.accessmanagement.entity.Role;
import com.medium.accessmanagement.entity.InputRelationship;
import com.medium.accessmanagement.entity.Resource;
import com.medium.accessmanagement.repository.AccessRepository;
import com.medium.accessmanagement.repository.RoleRepository;
import com.medium.accessmanagement.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessService {

    @Autowired
    AccessRepository accessRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ResourceRepository resourceRepository;

    public Access createAccess(InputRelationship body){
        Role role = roleRepository.findByNameAndRoleGroup(body.getRole(), body.getRoleGroupName());
        Resource resource = resourceRepository.findByRoute(body.getRoute(), body.getRouteMethod());

        Access access = new Access();
        access.setRole(role);
        access.setResource(resource);

        return accessRepository.save(access);
    }
}
