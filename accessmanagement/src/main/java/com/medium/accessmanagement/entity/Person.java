package com.medium.accessmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String personId;

    @JsonIgnoreProperties("person")
    @Relationship(type = "BELONGS_TO")
    public Set<Organization> organizations;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Set<Organization> getOrganization() {
        return organizations;
    }

    public void setOrganization(Set<Organization> organizations) {
        this.organizations = organizations;
    }
}
