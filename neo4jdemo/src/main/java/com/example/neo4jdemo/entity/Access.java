package com.example.neo4jdemo.entity;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "HAS_ACCESS")
public class Access {
    @Id
    @GeneratedValue
    private Long id;

    private String status;

    @StartNode
    private Group group;

    @EndNode
    private Resource resource;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
