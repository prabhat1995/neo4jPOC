package com.medium.accessmanagement.entity;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "HAS_ACCESS")
public class Access {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Role role;

    @EndNode
    private Resource resource;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
