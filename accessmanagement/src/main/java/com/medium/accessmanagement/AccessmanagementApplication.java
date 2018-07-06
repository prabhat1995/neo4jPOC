package com.medium.accessmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("com.medium.accessmanagement.repository")
public class AccessmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessmanagementApplication.class, args);
	}
}
