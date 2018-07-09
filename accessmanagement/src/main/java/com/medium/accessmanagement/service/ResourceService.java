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

    public Resource getResourceByName(String name){
        return resourceRepository.findByName(name);
    }

    public Boolean checkPersonAccess(InputRelationship body){

        Collection<Organization> orgs = organizationRepository.getOrganizationsForPerson(body.getPersonId());

        String role = null;
        List<String> roles = new ArrayList<>();
        Iterator<Organization> organizations = orgs.iterator();
        while (organizations.hasNext()) {
            Organization group = organizations.next();
            Iterator<Member> members = group.getMembers().iterator();
            while (members.hasNext()){
                Member member = members.next();
                roles.add(member.getRole());
            }
        }
        if(Arrays.asList(roles).contains("admin")){
            role = "admin";
        } else if (Arrays.asList(roles).contains("author")){
            role = "author";
        } else if (Arrays.asList(roles).contains("consumer"))
            role = "consumer";

        String[] tokens = body.getRoute().split("/");
        String microservice = tokens[1];
        Collection<Resource> routes =  resourceRepository.findByMicroserviceId(microservice);
        Iterator<Resource> resources = routes.iterator();
        String route = null;
        while (resources.hasNext()) {
            Resource resource = resources.next();
            if(Pattern.compile(resource.getRoute()).matcher(body.getRoute()).matches()){
                route = resource.getRoute();
                break;
            } else {
                System.out.println("route did not match");
                return false;
            }
        }
        return resourceRepository.checkAccess(role, route, routeMethod);
    }
}
