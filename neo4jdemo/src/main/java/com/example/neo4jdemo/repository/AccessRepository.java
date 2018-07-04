package com.example.neo4jdemo.repository;

import com.example.neo4jdemo.entity.Access;
import com.example.neo4jdemo.entity.Group;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Collection;

public interface AccessRepository extends Neo4jRepository<Access, Long> {
}
