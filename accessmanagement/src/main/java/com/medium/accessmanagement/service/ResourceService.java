package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.*;
import com.medium.accessmanagement.repository.OrganizationRepository;
import com.medium.accessmanagement.repository.ResourceRepository;
import com.medium.accessmanagement.repository.RoleGroupRepository;
import com.medium.accessmanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    RoleGroupRepository roleGroupRepository;

    @Autowired
    HasGroupService hasGroupService;

    @Autowired
    HasRoleService hasRoleService;

    @Autowired
    MemberService memberService;

    @Autowired
    RoleAccessService roleAccessService;

    @Autowired
    PersonService personService;

    @Autowired
    OrganizationService organizationService;

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

    public String createAllNodeAndRelationship(){
        InputRelationship body = new InputRelationship();

        // post all roleGroups
        RoleGroup roleGroup = new RoleGroup();
        roleGroup.setName("organization");
        roleGroupRepository.save(roleGroup);

        // post all roles with role group
        List<Role> roles = new ArrayList<>();
        Role admin = new Role();
        admin.setName("admin");
        roles.add(admin);
        Role author = new Role();
        author.setName("author");
        roles.add(author);
        Role consumer = new Role();
        consumer.setName("consumer");
        roles.add(consumer);
        Iterator<Role> roleIterator = roleRepository.saveAll(roles).iterator();
        while (roleIterator.hasNext()){
            Role role = roleIterator.next();
            body.setRoleGroupName(roleGroup.getName());
            body.setRole(role.getName());
            hasRoleService.createRelationShip(body);
        }

        // get all persons

        String resourceUrl = "http://172.16.18.114:3005/v1/customers/persons";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PersonResponse> personEntity = restTemplate.getForEntity(resourceUrl, PersonResponse.class);
        List<TempPerson> personList = Arrays.asList(personEntity.getBody().getData());

        // create all persons

        Iterator<TempPerson> personIterator = personList.iterator();
        while (personIterator.hasNext()){
            TempPerson personObject = personIterator.next();
            Person person = new Person();
            person.setPersonId(personObject.get_id());
            person.setEmail(personObject.getEmail());

            personService.savePerson(person);
        }

        // get all organizations

        resourceUrl = "http://172.16.18.114:3005/v1/customers/organizations";
        ResponseEntity<OrganizationResponse> organizationEntity = restTemplate.getForEntity(resourceUrl, OrganizationResponse.class);
        List<TempOrganization> organizationList = Arrays.asList(organizationEntity.getBody().getData());

        Iterator<TempOrganization> tempOrganizationIterator = organizationList.iterator();
        while (tempOrganizationIterator.hasNext()){
            TempOrganization tempOrganization = tempOrganizationIterator.next();
            Organization organization = new Organization();
            organization.setOrganizationId(tempOrganization.getId());
            organization.setName(tempOrganization.getName());
            organization.setStatus(tempOrganization.getStatus());

            organizationService.saveOrganization(organization);
        }

        // get all userStatus

        resourceUrl = "http://localhost:3005/v1/customers/userstatus";
        ResponseEntity<UserStatusResponse> userstatusEntity = restTemplate.getForEntity(resourceUrl, UserStatusResponse.class);
        List<UserStatus> userstatusList = Arrays.asList(userstatusEntity.getBody().getData());

        // create relationship b/w orgs and roleGroup
        while (tempOrganizationIterator.hasNext()){
            TempOrganization tempOrganization = tempOrganizationIterator.next();
            body.setRoleGroupName("organization");
            body.setOrganizationId(tempOrganization.getId());

            hasGroupService.createRelationship(body);
        }

        // create relation b/w person and organization
        // create relation b/w person and role
        Iterator<UserStatus> userStatusIterator = userstatusList.iterator();
        while (userStatusIterator.hasNext()){
            UserStatus userStatus = userStatusIterator.next();
            body.setPersonId(userStatus.getUser_id());
            body.setOrganizationId(userStatus.getOrganization_id());
            body.setStatus(userStatus.getStatus());
            body.setRoleGroupName(roleGroup.getName());
            if(userStatus.getRole()[0] == null){
                continue;
            } else {
                body.setRole(userStatus.getRole()[0]);
            }
            memberService.createRelationship(body);
            roleAccessService.createRelationship(body);
        }
        return "success";
    }
}
