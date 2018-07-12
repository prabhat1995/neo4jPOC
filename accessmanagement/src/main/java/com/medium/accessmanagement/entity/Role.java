package com.medium.accessmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.Set;

public class Role {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonIgnoreProperties("role")
    @Relationship(type = "HAS_ACCESS")
    public Set<Resource> resources;

    @JsonIgnoreProperties("role")
    @Relationship(type = "HAS_ROLE", direction = Relationship.INCOMING)
    public Set<HasRole> groupRoles;

    @JsonIgnoreProperties("role")
    @Relationship(type = "IS_A", direction = Relationship.INCOMING)
    public Set<RoleAccess> personRoles;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }
}
