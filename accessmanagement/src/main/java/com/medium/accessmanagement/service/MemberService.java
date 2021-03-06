package com.medium.accessmanagement.service;

import com.medium.accessmanagement.entity.InputRelationship;
import com.medium.accessmanagement.entity.Member;
import com.medium.accessmanagement.entity.Organization;
import com.medium.accessmanagement.entity.Person;
import com.medium.accessmanagement.repository.MemberRepository;
import com.medium.accessmanagement.repository.OrganizationRepository;
import com.medium.accessmanagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    MemberRepository memberRepository;

    public Member createRelationship(InputRelationship body){
        Person person = personRepository.findByPersonId(body.getPersonId());
        Organization organization = organizationRepository.findByOrganizationId(body.getOrganizationId());

        Member member = new Member();
        member.setPerson(person);
        member.setOrganization(organization);
        member.setStatus(body.getStatus());

        return memberRepository.save(member);
    }

    public Member updateRelationship(InputRelationship body){

        Member member = memberRepository.findByPersonIdAndOrganizationId(body.getPersonId(), body.getOrganizationId());
        member.setStatus(body.getStatus());

        return memberRepository.save(member);
    }

    public void deleteMember(String orgId, String personId){

        Member member = memberRepository.findByPersonIdAndOrganizationId(personId, orgId);

        memberRepository.deleteRoleAccess(personId, orgId);
        memberRepository.delete(member);

        return;
    }
}
