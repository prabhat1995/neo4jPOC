package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.Organization;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface OrganizationRepository extends Neo4jRepository<Organization, Long> {
    public Organization findByOrganizationId(@Param("organizationId") String organizationId);

    @Query("Match (o:Organization)<-[r:BELONGS_TO]-(p:Person {personId: {personId}}) return o,r,p")
    public Collection<Organization> getOrganizationsForPerson(@Param("personId") String personId);
}
