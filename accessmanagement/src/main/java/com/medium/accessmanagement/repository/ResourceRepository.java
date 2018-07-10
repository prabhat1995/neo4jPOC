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

    @Query("Match (r:Resource {route: {route}, method: {method}})<-[rel:HAS_ACCESS]-(g:Group {accessOfRole: {accessOfRole}}) return count(g) > 0 as c")
    public Boolean checkAccess(@Param("accessOfRole") String accessOfRole, @Param("route") String route, @Param("method") String method);
}
