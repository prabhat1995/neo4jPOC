package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.Role;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends Neo4jRepository<Role, Long> {
    public Role findByName(@Param("name") String name);
}
