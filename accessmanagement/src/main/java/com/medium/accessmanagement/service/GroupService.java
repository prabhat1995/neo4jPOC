package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.Role;
import com.medium.accessmanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    RoleRepository roleRepository;

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public Role findByName(String name){
        return roleRepository.findByName(name);
    }
}
