package com.example.neo4jdemo.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class Resource {

    @Id
    @GeneratedValue
    private Long id;

    private String route;

    @Relationship(type = "HAS_ACCESS", direction = Relationship.INCOMING)
    public Set<Access> hasAccessTo;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
