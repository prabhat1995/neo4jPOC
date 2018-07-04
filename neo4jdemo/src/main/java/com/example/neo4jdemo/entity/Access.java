package com.example.neo4jdemo.entity;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "HAS_ACCESS")
public class Access {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Group group;

    @EndNode
    private Resource resource;
}
