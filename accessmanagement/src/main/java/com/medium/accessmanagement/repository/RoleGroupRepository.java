package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.RoleGroup;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface RoleGroupRepository extends Neo4jRepository<RoleGroup, Long> {

    public RoleGroup findByName(@Param("name") String name);
}
