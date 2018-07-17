package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.Role;
import com.medium.accessmanagement.entity.RoleAccess;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface RoleAccessRepository extends Neo4jRepository<RoleAccess, Long> {

    @Query("Match (role:Role {name:{role}})<-[roleRel:HAS_ROLE]-(rg:RoleGroup {name:{roleGroupName}})<-[groupRel:HAS_GROUP]-(org:Organization {organizationId:{organizationId}})<-[member:BELONGS_TO {status:\"active\"}]-(p:Person {personId: {personId}})-[rel:IS_A {organizationIds:[{organizationId}]}]->(role:Role {name:{role}}) return rel")
    public RoleAccess findOrganizationIdsForPersonRole(@Param("role") String role, @Param("personId") String personId, @Param("organizationId") String organizationId, @Param("roleGroupName") String roleGroupName);

    @Query("Match (role:Role)<-[roleRel:HAS_ROLE]-(rg:RoleGroup {name:{roleGroupName}})<-[groupRel:HAS_GROUP]-(org:Organization {organizationId:{organizationId}})<-[member:BELONGS_TO {status:\"active\"}]-(p:Person {personId: {personId}})-[rel:IS_A {organizationIds:[{organizationId}]}]->(role:Role) return rel")
    public RoleAccess findRoleAccessForPerson(@Param("personId") String personId, @Param("organizationId") String organizationId, @Param("roleGroupName") String roleGroupName);
}
