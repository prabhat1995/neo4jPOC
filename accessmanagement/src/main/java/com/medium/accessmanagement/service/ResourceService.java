package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.InputRelationship;
import com.medium.accessmanagement.entity.Member;
import com.medium.accessmanagement.entity.Organization;
import com.medium.accessmanagement.entity.Resource;
import com.medium.accessmanagement.repository.OrganizationRepository;
import com.medium.accessmanagement.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Pattern;

@Component
public class ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    public Resource saveResource(Resource resource){
        return resourceRepository.save(resource);
    }

    public Resource getResourceByName(String route, String method){
        return resourceRepository.findByRoute(route, method);
    }

    public Boolean checkPersonAccess(InputRelationship body){

        Collection<Organization> orgs = organizationRepository.getOrganizationsForPerson(body.getPersonId());

        String role = null;
        Set<String> roles = new HashSet<String>();
        Iterator<Organization> organizations = orgs.iterator();
        while (organizations.hasNext()) {
            Organization group = organizations.next();
            Iterator<Member> members = group.getMembers().iterator();
            while (members.hasNext()){
                Member member = members.next();
                roles.add(member.getRole());
            }
        }

        if(roles.contains("admin")){
            role = "admin";
        } else if (roles.contains("author")){
            role = "author";
        } else if (roles.contains("consumer")) {
            role = "consumer";
        }else {
            System.out.println("role not found");
            return false;
        }

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
        return resourceRepository.checkAccess(role, route, body.getRouteMethod());
    }
}
