package com.example.neo4jdemo.repository;

import com.example.neo4jdemo.entity.Access;
import com.example.neo4jdemo.entity.Member;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MemberRepository extends Neo4jRepository<Member, Long> {
}
