package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.*;
import com.medium.accessmanagement.repository.OrganizationRepository;
import com.medium.accessmanagement.repository.ResourceRepository;
import com.medium.accessmanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Collection<Resource> saveResource(Collection<Resource> resources){
        List<Resource> output = new ArrayList<>();
        Iterator<Resource> routes = resources.iterator();
        while (routes.hasNext()) {
            Resource resource = routes.next();
            output.add(resourceRepository.save(resource));
        }
        return output;
    }

    public Resource getResourceByName(String route, String method){
        return resourceRepository.findByRoute(route, method);
    }

    public static final String[] microservices = new String[] {"account","auth","authoring","export","fileupload","notification","customer","feedback","search"};
    public Boolean checkPersonAccess(InputRelationship body){
        String microservice = null;
        String[] tokens = body.getRoute().split("/");
        for(int i=0; i<tokens.length; i++){
            if(Arrays.asList(microservices).contains(tokens[i])){
                microservice = tokens[i];
                break;
            }
        }
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

        Boolean accessFlage = false;

        Iterator<Role> roleIterator = personRoles.iterator();
        while (roleIterator.hasNext()) {
            Role role = roleIterator.next();
            Boolean access = resourceRepository.checkAccess(body.getPersonId(), role.getRoleId(), route, body.getRouteMethod());
            if (access) {
                accessFlage = true;
            }
        }
        return accessFlage;
    }

    public Collection<Resource> getAllRoutesByRole(InputRelationship body){
        return resourceRepository.getRoles(body.getEmail(), body.getRole(), body.getRoleGroupName());
    }
}
