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

        Collection<Role> personRoles = roleRepository.findByPersonId(body.getPersonId());

        String priorityRole = null;
        String priorityRoleId = null;
        Set<String> roles = new HashSet<String>();
        Iterator<Role> roleIterator = personRoles.iterator();
        while (roleIterator.hasNext()) {
            Role role = roleIterator.next();
            roles.add(role.getName());
        }

        if(roles.contains("admin")){
            priorityRole = "admin";
        } else if (roles.contains("author")){
            priorityRole = "author";
        } else if (roles.contains("consumer")) {
            priorityRole = "consumer";
        }else {
            System.out.println("role not found");
            return false;
        }

        priorityRoleId = roleRepository.findByName(priorityRole).getRoleId();
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
        return resourceRepository.checkAccess(body.getPersonId(), priorityRoleId, route, body.getRouteMethod());
    }
}
