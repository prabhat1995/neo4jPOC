package com.medium.accessmanagement.entity;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "HAS_GROUP")
public class HasGroup {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Organization organization;

    @EndNode
    private RoleGroup roleGroup;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public RoleGroup getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(RoleGroup roleGroup) {
        this.roleGroup = roleGroup;
    }
}
