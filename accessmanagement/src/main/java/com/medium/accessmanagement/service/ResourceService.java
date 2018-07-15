package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.*;
import com.medium.accessmanagement.repository.OrganizationRepository;
import com.medium.accessmanagement.repository.ResourceRepository;
import com.medium.accessmanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    RoleRepository roleRepository;

    public Resource saveResource(Resource resource){
        return resourceRepository.save(resource);
    }

    public Resource getResourceByName(String route, String method){
        return resourceRepository.findByRoute(route, method);
    }

    public Boolean checkPersonAccess(InputRelationship body){

        String[] tokens = body.getRoute().split("/");
        String microservice = tokens[2];
        Collection<Resource> routes =  resourceRepository.findByMicroserviceId(microservice);
        Iterator<Resource> resources = routes.iterator();
        String route = null;
        Boolean matchFlag = false;
        while (resources.hasNext()) {
            Resource resource = resources.next();
            if(Pattern.compile(resource.getRoute()).matcher(body.getRoute()).matches()){
                route = resource.getRoute();
                matchFlag = true;
                break;
            }
        }
        if(!matchFlag){
            System.out.println("route did not match");
            return false;
        }

        Collection<Role> personRoles = roleRepository.findByPersonId(body.getPersonId());

        Boolean access = false;

        Iterator<Role> roleIterator = personRoles.iterator();
        while (roleIterator.hasNext()) {
            Role role = roleIterator.next();
            access = resourceRepository.checkAccess(body.getPersonId(), role.getRoleId(), route, body.getRouteMethod());
        }
        return access;
    }
}
