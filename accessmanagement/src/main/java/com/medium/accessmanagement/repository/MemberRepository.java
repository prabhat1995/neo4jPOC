package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.Member;
import com.medium.accessmanagement.entity.RoleAccess;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface MemberRepository extends Neo4jRepository<Member, Long> {

    @Query("MATCH (p:Person {personId: {personId}})-[r:BELONGS_TO]-> (org:Organization {organizationId: {organizationId}}) RETURN p, org, r")
    public Member findByPersonIdAndOrganizationId(@Param("personId") String personId, @Param("organizationId") String organizationId);

    @Query("MATCH (p:Person {personId: {personId}})-[rel:IS_A {organizationIds: [{organizationId}]}]-> (role:Role) DETACH DELETE rel")
    public void deleteRoleAccess(@Param("personId") String personId, @Param("organizationId") String organizationId);
}
