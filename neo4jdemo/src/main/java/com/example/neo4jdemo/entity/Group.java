package com.example.neo4jdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NodeEntity
public class Group {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonIgnoreProperties("group")
    @Relationship(type = "BELONGS_TO", direction = Relationship.INCOMING)
    public Set<Member> members;

    @JsonIgnoreProperties("group")
    @Relationship(type = "HAS_ACCESS", direction = Relationship.OUTGOING)
    public List<Resource> resources;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
