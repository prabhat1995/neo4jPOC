package com.medium.accessmanagement.entity;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "BELONGS_TO")
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String status;

    @StartNode
    private Person person;

    @EndNode
    private Organization organization;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
