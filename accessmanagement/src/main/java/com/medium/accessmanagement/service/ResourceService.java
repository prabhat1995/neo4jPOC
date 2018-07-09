package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.Resource;
import com.medium.accessmanagement.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    public Resource saveResource(Resource resource){
        return resourceRepository.save(resource);
    }

    public Resource getResourceByName(String name){
        return resourceRepository.findByName(name);
    }
}
