package com.example.neo4jdemo.service;

import com.example.neo4jdemo.entity.Group;
import com.example.neo4jdemo.entity.Person;
import com.example.neo4jdemo.repository.GroupRepository;
import com.example.neo4jdemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group saveGroup(Group group){
        return groupRepository.save(group);
    }

    public Group getGroupByName(String name){
        return groupRepository.findByName(name);
    }
}
