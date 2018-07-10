package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.Access;
import com.medium.accessmanagement.entity.Group;
import com.medium.accessmanagement.entity.InputRelationship;
import com.medium.accessmanagement.entity.Resource;
import com.medium.accessmanagement.repository.AccessRepository;
import com.medium.accessmanagement.repository.GroupRepository;
import com.medium.accessmanagement.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessService {

    @Autowired
    AccessRepository accessRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ResourceRepository resourceRepository;

    public Access createAccess(InputRelationship body){
        Group group = groupRepository.findByName(body.getGroupName());
        Resource resource = resourceRepository.findByRoute(body.getRoute(), body.getRouteMethod());

        Access access = new Access();
        access.setGroup(group);
        access.setResource(resource);

        return accessRepository.save(access);
    }
}
