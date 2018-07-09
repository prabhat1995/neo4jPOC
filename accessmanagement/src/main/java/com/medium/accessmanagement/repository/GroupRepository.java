package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.Group;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends Neo4jRepository<Group, Long> {
    public Group findByName(@Param("name") String name);
}
