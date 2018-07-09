package com.medium.accessmanagement.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GroupRepository extends Neo4jRepository<String, Long> {
}
