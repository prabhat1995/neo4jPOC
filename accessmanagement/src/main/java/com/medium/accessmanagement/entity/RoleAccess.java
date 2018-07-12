package com.medium.accessmanagement.entity;

import org.neo4j.ogm.annotation.*;

import java.util.Set;

@RelationshipEntity(type = "IS_A")
public class RoleAccess {

    @Id
    @GeneratedValue
    private Long id;

    private Set<String> organizationIds;

    @StartNode
    private Person person;

    @EndNode
    private Role role;

    public Set<String> getOrganizationIds() {
        return organizationIds;
    }

    public void setOrganizationIds(Set<String> organizationIds) {
        this.organizationIds = organizationIds;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
