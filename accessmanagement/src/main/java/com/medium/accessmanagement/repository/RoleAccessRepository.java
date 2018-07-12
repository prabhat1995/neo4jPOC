package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.RoleAccess;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface RoleAccessRepository extends Neo4jRepository<RoleAccess, String> {

    @Query("Match (role:Role {name:{role}})<-[rel:HAS_ROLE]-(rg:RoleGroup)<-[r:HAS_GROUP]-(org:Organization {organizationId:{organizationId}})<-[rel:BELONGS_TO {status:active}]-(p:Person {personId: {personId}})-[r:IS_A {organizationIds:{organizationId}}]->(role:Role {name:{role}})")
    public RoleAccess findOrganizationIdsForPersonRole(@Param("role") String role, @Param("personId") String personId, @Param("organizationId") String organizationId);
}
