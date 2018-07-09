package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.Access;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AccessRepository extends Neo4jRepository<Access, Long> {
}
