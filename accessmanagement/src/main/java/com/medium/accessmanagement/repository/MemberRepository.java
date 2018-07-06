package com.medium.accessmanagement.repository;

import com.medium.accessmanagement.entity.Member;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MemberRepository extends Neo4jRepository<Member, Long> {
}
