package com.example.neo4jdemo.repository;

import com.example.neo4jdemo.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    public Person findByName(@Param("name") String name);
}
