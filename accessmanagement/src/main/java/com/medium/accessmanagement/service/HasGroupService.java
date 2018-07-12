package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.HasGroup;
import com.medium.accessmanagement.entity.InputRelationship;
import com.medium.accessmanagement.entity.Organization;
import com.medium.accessmanagement.entity.RoleGroup;
import com.medium.accessmanagement.repository.HasGroupRepository;
import com.medium.accessmanagement.repository.OrganizationRepository;
import com.medium.accessmanagement.repository.RoleGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HasGroupService {

    @Autowired
    HasGroupRepository hasGroupRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    RoleGroupRepository roleGroupRepository;

    public HasGroup createRelationship(InputRelationship body){
        Organization organization = organizationRepository.findByOrganizationId(body.getOrganizationId());
        RoleGroup roleGroup = roleGroupRepository.findByName(body.getRoleGroupName());

        HasGroup relationship = new HasGroup();
        relationship.setOrganization(organization);
        relationship.setRoleGroup(roleGroup);

        return hasGroupRepository.save(relationship);
    }
}
