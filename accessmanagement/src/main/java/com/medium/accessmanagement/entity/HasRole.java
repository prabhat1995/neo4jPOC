package com.medium.accessmanagement.entity;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "HAS_ROLE")
public class HasRole {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private RoleGroup roleGroup;

    @EndNode
    private Role role;

    public RoleGroup getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(RoleGroup roleGroup) {
        this.roleGroup = roleGroup;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
