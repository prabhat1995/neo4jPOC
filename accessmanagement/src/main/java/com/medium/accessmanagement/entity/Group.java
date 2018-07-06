package com.medium.accessmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.Set;

public class Group {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String accessOfRole;
    private Set<String> methods;

    @JsonIgnoreProperties("group")
    @Relationship(type = "HAS_ACCESS")
    public Set<Resource> resources;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessOfRole() {
        return accessOfRole;
    }

    public void setAccessOfRole(String accessOfRole) {
        this.accessOfRole = accessOfRole;
    }

    public Set<String> getMethods() {
        return methods;
    }

    public void setMethods(Set<String> methods) {
        this.methods = methods;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }
}
