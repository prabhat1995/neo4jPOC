package com.example.neo4jdemo.service;

import com.example.neo4jdemo.entity.Access;
import com.example.neo4jdemo.entity.Group;
import com.example.neo4jdemo.entity.InputRelationship;
import com.example.neo4jdemo.entity.Resource;
import com.example.neo4jdemo.repository.AccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessService {

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ResourceService resourceService;

    public Access createRelationship(InputRelationship body){
        Group group = groupService.getGroupByName(body.getGroupName());
        Resource resource = resourceService.getResourceByRoute(body.getRoute());
        Access newRelation = new Access();
        newRelation.setGroup(group);
        newRelation.setResource(resource);
        newRelation.setStatus("true");

         return accessRepository.save(newRelation);

    }
}
