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

import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    RoleGroupRepository roleGroupRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    HasRoleRepository hasRoleRepository;

    public HasRole saveRole(InputRelationship body){

        Role role = new Role();
        String uuid = UUID.randomUUID().toString();
        role.setRoleId(uuid);
        role.setName(body.getRole());
        Role createdRole =  roleRepository.save(role);

        RoleGroup roleGroup = roleGroupRepository.findByName(body.getRoleGroupName());

        HasRole relationship = new HasRole();
        relationship.setRoleGroup(roleGroup);
        relationship.setRole(createdRole);

        return hasRoleRepository.save(relationship);
    }
}
