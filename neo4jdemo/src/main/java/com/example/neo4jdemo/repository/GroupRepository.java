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
}
