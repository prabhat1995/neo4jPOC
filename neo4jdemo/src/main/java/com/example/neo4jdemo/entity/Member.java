package com.example.neo4jdemo.entity;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "BELONGS_TO")
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Person person;

    @EndNode
    private Group group;

}
