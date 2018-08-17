package com.medium.accessmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collection;
import java.util.Set;

@NodeEntity
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    private String roleId;
    private String name;

    @JsonIgnoreProperties("role")
    @Relationship(type = "HAS_ACCESS")
    private Collection<Resource> resources;

    @JsonIgnoreProperties("role")
    @Relationship(type = "HAS_ROLE", direction = Relationship.INCOMING)
    private Set<HasRole> groupRoles;

    @JsonIgnoreProperties("role")
    @Relationship(type = "IS_A", direction = Relationship.INCOMING)
    private Set<RoleAccess> personRoles;

    public Long getId() {
        return id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Resource> getResources() {
        return resources;
    }

    public void setResources(Collection<Resource> resources) {
        this.resources = resources;
    }

    public Set<HasRole> getGroupRoles() {
        return groupRoles;
    }

    public void setGroupRoles(Set<HasRole> groupRoles) {
        this.groupRoles = groupRoles;
    }

    public Set<RoleAccess> getPersonRoles() {
        return personRoles;
    }

    public void setPersonRoles(Set<RoleAccess> personRoles) {
        this.personRoles = personRoles;
    }
}
