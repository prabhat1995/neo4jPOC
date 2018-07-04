package com.example.neo4jdemo.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class Group {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "BELONGS_TO", direction = Relationship.INCOMING)
    public Set<Member> belongto;

    @Relationship(type = "HAS_ACCESS", direction = Relationship.OUTGOING)
    public Set<Access> hasAccessTo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
