package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.Resource;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface ResourceRepository extends Neo4jRepository<Resource, Long> {
    public Resource findByName(@Param("name") String name);

    @Query("Match (r:Resource {route: {route}})<-[r:HAS_ACCESS]-(g:Group {accessOfRole: {role}}) return count(g) > 0 as c")
    public Boolean checkAccess(@Param("accessOfRole") String role, @Param("route") String route);
}
