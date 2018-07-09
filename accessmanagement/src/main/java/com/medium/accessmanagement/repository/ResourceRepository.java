package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.Resource;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ResourceRepository extends Neo4jRepository<Resource, Long> {
    public Resource findByName(@Param("name") String name);

    @Query("Match (r:Resource {microserviceId: {microservice}}) return r")
    public Collection<Resource> findByMicroserviceId(@Param("microserviceId") String microservice);

    @Query("Match (r:Resource {route: {route}, method: {routeMethod}})<-[r:HAS_ACCESS]-(g:Group {accessOfRole: {role}}) return count(g) > 0 as c")
    public Boolean checkAccess(@Param("accessOfRole") String role, @Param("route") String route, @Param("method") String routeMethod);
}
