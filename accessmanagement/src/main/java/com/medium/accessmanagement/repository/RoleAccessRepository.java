package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.RoleAccess;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RoleAccessRepository extends Neo4jRepository<RoleAccess, String> {

    public RoleAccess find
}
