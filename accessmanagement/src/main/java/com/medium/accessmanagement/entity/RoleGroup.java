package com.medium.accessmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collection;
import java.util.Set;

@NodeEntity
public class RoleGroup {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonIgnoreProperties("roleGroup")
    @Relationship(type = "HAS_GROUP", direction = Relationship.INCOMING)
    public Set<HasGroup> organizationRoleGroups;

   // @JsonIgnoreProperties("roleGroup")
    @Relationship(type = "HAS_ROLE")
    public Collection<Role> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<HasGroup> getOrganizationRoleGroups() {
        return organizationRoleGroups;
    }

    public void setOrganizationRoleGroups(Set<HasGroup> organizationRoleGroups) {
        this.organizationRoleGroups = organizationRoleGroups;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
