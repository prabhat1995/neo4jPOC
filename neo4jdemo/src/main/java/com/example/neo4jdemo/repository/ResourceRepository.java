package com.example.neo4jdemo.repository;

import com.example.neo4jdemo.entity.Person;
import com.example.neo4jdemo.entity.Resource;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface ResourceRepository extends Neo4jRepository<Resource, Long> {
    public Resource findByRoute(@Param("route") String route);

}
