package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.HasRole;
import com.medium.accessmanagement.entity.InputRelationship;
import com.medium.accessmanagement.entity.Role;
import com.medium.accessmanagement.entity.RoleGroup;
import com.medium.accessmanagement.repository.HasRoleRepository;
import com.medium.accessmanagement.repository.RoleGroupRepository;
import com.medium.accessmanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HasRoleService {

    @Autowired
    HasRoleRepository hasRoleRepository;

    @Autowired
    RoleGroupRepository roleGroupRepository;

    @Autowired
    RoleRepository roleRepository;

    public HasRole createRelationShip(InputRelationship body){
        RoleGroup roleGroup = roleGroupRepository.findByName(body.getRoleGroupName());
        Role role = roleRepository.findByName(body.getRole());

        HasRole relationship = new HasRole();
        relationship.setRoleGroup(roleGroup);
        relationship.setRole(role);

        return hasRoleRepository.save(relationship);
    }
}
