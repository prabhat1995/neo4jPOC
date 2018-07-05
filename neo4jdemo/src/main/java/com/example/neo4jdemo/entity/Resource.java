package com.example.neo4jdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Resource {

    @Id
    @GeneratedValue
    private Long id;

    private String route;

    @JsonIgnoreProperties("resource")
    @Relationship(type = "HAS_ACCESS", direction = Relationship.INCOMING)
    public Set<Access> groupsWithAccess;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Set<Access> getGroupsWithAccess() {
        return groupsWithAccess;
    }

    public void setGroupsWithAccess(Set<Access> groupsWithAccess) {
        this.groupsWithAccess = groupsWithAccess;
    }
}
