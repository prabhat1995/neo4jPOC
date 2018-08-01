package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends Neo4jRepository<Person, Long> {
    public Person findByPersonId(@Param("personId") String personId);
    public Person findByEmail(@Param("email") String email);
}
