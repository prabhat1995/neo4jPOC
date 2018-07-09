package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.Group;
import com.medium.accessmanagement.entity.InputRelationship;
import com.medium.accessmanagement.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public Group saveGroup(Group group){
        return groupRepository.save(group);
    }

    public Group findByName(String name){
        return groupRepository.findByName(name);
    }
}
