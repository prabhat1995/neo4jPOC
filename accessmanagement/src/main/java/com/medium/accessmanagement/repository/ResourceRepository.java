package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.Resource;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ResourceRepository extends Neo4jRepository<Resource, Long> {

    @Query("Match (r:Resource {route: {route}, method: {method}}) return r")
    public Resource findByRoute(@Param("route") String route, @Param("method") String method);

    @Query("Match (r:Resource {microserviceId: {microserviceId}}) return r")
    public Collection<Resource> findByMicroserviceId(@Param("microserviceId") String microserviceId);

    @Query("Match (role:Role {roleId:{roleId}})<-[roleRel:HAS_ROLE]-(rg:RoleGroup)<-[groupRel:HAS_GROUP]-(org:Organization {status:\"active\"})<-[member:BELONGS_TO {status:\"active\"}]-(p:Person {personId: {personId}})-[personRel:IS_A]->(role:Role {roleId:{roleId}})-[rel:HAS_ACCESS]->(res:Resource {route:{route}, method:{method}}) return count(role) > 0 as c")
    public Boolean checkAccess(@Param("personId") String personId, @Param("roleId") String roleId, @Param("route") String route, @Param("method") String method);
}
