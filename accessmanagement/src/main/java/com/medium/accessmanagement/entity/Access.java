package com.medium.accessmanagement.entity;

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
