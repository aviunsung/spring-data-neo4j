/**
 *
 */
package demo.neo4j.repository;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import demo.neo4j.entity.Person;

/**
 * @author avinash
 *
 */
@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {
	Person findByName(String name);

	@Query("MATCH (person:Person)-[rel:ACTED_IN]-(movie:Movie) RETURN person,rel,movie LIMIT {limit}")
	Collection<Person> graph(@Param("limit") int limit);

	@Query("MATCH(friends:Person)<-[rel:FRIEND]-(person:Person) WHERE person.name={name} Return friends")
	Collection<Person> getFriends(@Param("name") String name);

}
