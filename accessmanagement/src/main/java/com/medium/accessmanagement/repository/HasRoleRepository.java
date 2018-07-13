package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.HasRole;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface HasRoleRepository extends Neo4jRepository<HasRole, Long> {
}
