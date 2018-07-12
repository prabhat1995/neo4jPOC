package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.RoleGroup;
import com.medium.accessmanagement.repository.RoleGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleGroupService {

    @Autowired
    RoleGroupRepository roleGroupRepository;

    public RoleGroup saveRoleGroup(RoleGroup roleGroup){
        return roleGroupRepository.save(roleGroup);
    }
}
