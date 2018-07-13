package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.Role;
import com.medium.accessmanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role saveRole(Role role){

        String uuid = UUID.randomUUID().toString();
        role.setRoleId(uuid);
        return roleRepository.save(role);
    }
}
