package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.HasGroup;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface HasGroupRepository extends Neo4jRepository<HasGroup, Long> {
}
