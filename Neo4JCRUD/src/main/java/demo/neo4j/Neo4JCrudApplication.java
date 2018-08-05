package demo.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("demo.neo4j.repository")

public class Neo4JCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neo4JCrudApplication.class, args);
	}
}
