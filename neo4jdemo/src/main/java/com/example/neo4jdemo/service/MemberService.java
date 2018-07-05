package com.example.neo4jdemo.service;

import com.example.neo4jdemo.entity.Group;
import com.example.neo4jdemo.entity.Member;
import com.example.neo4jdemo.entity.Person;
import com.example.neo4jdemo.repository.GroupRepository;
import com.example.neo4jdemo.repository.MemberRepository;
import com.example.neo4jdemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PersonRepository personRepository;

    public Member createRelation(String personName,String groupName){

        Person person = personRepository.findByName(personName);
        Group group = groupRepository.findByName(groupName);

        Member newRelation = new Member();
        newRelation.setGroup(group);
        newRelation.setPerson(person);
        return memberRepository.save(newRelation);
    }
}
