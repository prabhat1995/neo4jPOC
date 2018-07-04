package com.example.neo4jdemo.service;

import com.example.neo4jdemo.entity.Group;
import com.example.neo4jdemo.entity.Resource;
import com.example.neo4jdemo.repository.GroupRepository;
import com.example.neo4jdemo.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;

    public Resource saveResource(Resource resource){
        return resourceRepository.save(resource);
    }

    public Resource getResourceByRoute(String route){
        return resourceRepository.findByRoute(route);
    }
}
