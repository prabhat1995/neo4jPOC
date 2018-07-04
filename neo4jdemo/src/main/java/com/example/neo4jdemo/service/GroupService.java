package com.example.neo4jdemo.service;

import com.example.neo4jdemo.entity.Group;
import com.example.neo4jdemo.entity.Person;
import com.example.neo4jdemo.repository.GroupRepository;
import com.example.neo4jdemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Component
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    private List<Group> groups;

    public Group saveGroup(Group group){
        return groupRepository.save(group);
    }

    public Group getGroupByName(String name){
        return groupRepository.findByName(name);
    }

    public List<Group> getAllGroups(){
        Iterator<Group> ite =  groupRepository.findAll().iterator();
        groups = new ArrayList<>();
        while (ite.hasNext()) {
            groups.add(ite.next());
        }

        return groups;

    }

    public Collection<Group> getGroupDetails(String name){
        Collection<Group> gp = groupRepository.graph(name);
        return gp;
    }

}
