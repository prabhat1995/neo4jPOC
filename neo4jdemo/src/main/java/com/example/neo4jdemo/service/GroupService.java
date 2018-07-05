package com.example.neo4jdemo.service;

import com.example.neo4jdemo.entity.Group;
import com.example.neo4jdemo.entity.Member;
import com.example.neo4jdemo.entity.Person;
import com.example.neo4jdemo.repository.GroupRepository;
import com.example.neo4jdemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Transactional(readOnly = true)
    public List<Map<String,Object>> getGroupWithMembers(){
        Collection<Group> gp = groupRepository.graph2();
        return toD3Format(gp);
        //return  gp;
    }


    private List<Map<String,Object>> toD3Format(Collection<Group> groups) {

        List<Map<String,Object>> response = new ArrayList<>();
        Iterator<Group> result = groups.iterator();
        while (result.hasNext()) {
            Map<String, Object> output = new HashMap<String, Object>(2);
            Group group = result.next();
            output.put("group", group.getName());
            Iterator<Member> members = group.getMembers().iterator();
            List<String> persons = new ArrayList();
            while (members.hasNext()){
                Member member = members.next();
                persons.add(member.getPerson().getName());
            }
            output.put("persons", persons);
            response.add(output);

        }
        return response;
    }

    public Boolean checkPersonAccess(String personName, String route){
        return groupRepository.checkAccess(personName,route);
    }

}
