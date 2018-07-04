package com.example.neo4jdemo.repository;

import com.example.neo4jdemo.entity.Person;
import com.example.neo4jdemo.entity.Resource;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ResourceRepository extends Neo4jRepository<Resource, Long> {
    public Resource findByRoute(@Param("route") String route);

    @Query("match (n:Group)-[r:HAS_ACCESS]->(t:Resource { route: {route}}) return n,r,t")
    public Collection<Resource> getResourceDetail(@Param("route") String route);
}
