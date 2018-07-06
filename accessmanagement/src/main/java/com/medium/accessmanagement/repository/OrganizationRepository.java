package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.Organization;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface OrganizationRepository extends Neo4jRepository<Organization, Long> {
    public Organization findByOrganizationId(@Param("organizationId") String organizationId);
}
