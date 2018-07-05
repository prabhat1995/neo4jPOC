package com.example.neo4jdemo.repository;

import com.example.neo4jdemo.entity.Group;
import com.example.neo4jdemo.entity.Person;
import com.example.neo4jdemo.entity.Resource;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface GroupRepository extends Neo4jRepository<Group, Long> {

    public Group findByName(@Param("name") String name);

    @Query("Match (g:Group { name : {name}})-[r:HAS_ACCESS]->(t:Resource) return g,r,t")
    public Collection<Group> graph(@Param("name") String name);

    @Query("Match (g:Group)<-[r:BELONGS_TO]-(p:Person) return g,r,p ")
    public Collection<Group> graph2();

    @Query("Match (p:Person { name: {personName}})-[r:BELONGS_TO]->(g:Group)-[:HAS_ACCESS]->(res:Resource { route: {route}}) return count(p) > 0 as c ")
    public Boolean checkAccess(@Param("personName") String personName,@Param("route") String route);
}
