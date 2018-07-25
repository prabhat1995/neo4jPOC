package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.Role;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface RoleRepository extends Neo4jRepository<Role, Long> {

    public Role findByName(@Param("name") String name);

    @Query("Match (p:Person {personId: {personId}})-[rel:IS_A]->(role:Role) return role, rel")
    public Collection<Role> findByPersonId(@Param("personId") String personId);

    @Query("Match (r:Role {name:{name}})<-[rel:HAS_ROLE]-(rg:RoleGroup {name:{roleGroupName}}) return r")
    public Role findByNameAndRoleGroup(@Param("name") String name, @Param("roleGroupName") String roleGroupName);
}
