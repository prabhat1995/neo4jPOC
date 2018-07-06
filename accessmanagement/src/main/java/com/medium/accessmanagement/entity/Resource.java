package com.medium.accessmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

public class Resource {
    @Id
    @GeneratedValue
    private Long id;

    private String route;

    @JsonIgnoreProperties("resource")
    @Relationship(type = "HAS_ACCESS", direction = Relationship.INCOMING)
    public Set<Group> groups;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
