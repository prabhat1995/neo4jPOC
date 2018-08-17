package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.Resource;
import com.medium.accessmanagement.entity.Role;
import com.medium.accessmanagement.entity.RoleGroup;
import com.medium.accessmanagement.entity.Routes;
import com.medium.accessmanagement.repository.ResourceRepository;
import com.medium.accessmanagement.repository.RoleGroupRepository;
import com.medium.accessmanagement.repository.RoleRepository;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleGroupService {

    @Autowired
    RoleGroupRepository roleGroupRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ResourceRepository resourceRepository;

    public RoleGroup saveRoleGroup(RoleGroup roleGroup){
        return roleGroupRepository.save(roleGroup);
    }

    public Collection<Routes> getAllRoutes(){
        List<Routes> routes = new ArrayList();
        Iterator<RoleGroup> roleGroupIterator = roleGroupRepository.findAll().iterator();
        while (roleGroupIterator.hasNext()){
            RoleGroup roleGroup = roleGroupIterator.next();
            Collection<Role> roles = roleRepository.findByRoleGroup(roleGroup.getName());
            Iterator<Role> roleIterator = roles.iterator();
            while (roleIterator.hasNext()){
                Role role = roleIterator.next();
                Collection<Resource> resources = resourceRepository.findByRoleAndRoleGroup(role.getName(), roleGroup.getName());
                Iterator<Resource> resourceIterator = resources.iterator();
                while (resourceIterator.hasNext()){
                    Resource resource = resourceIterator.next();

                    Routes route = new Routes();
                    route.setRoleGroup(roleGroup.getName());
                    route.setRole(role.getName());
                    route.setRoute(resource.getRoute());
                    route.setMethod(resource.getMethod());
                    route.setMicroserviceId(resource.getMicroserviceId());

                    routes.add(route);
                }
            }
        }
        return routes;
    }
}
