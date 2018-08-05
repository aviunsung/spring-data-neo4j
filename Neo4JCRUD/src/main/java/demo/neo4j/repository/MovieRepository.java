/**
 *
 */
package demo.neo4j.repository;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import demo.neo4j.entity.Movie;

/**
 * @author avinash
 *
 */
@Repository
public interface MovieRepository extends Neo4jRepository<Movie, Long> {
	Movie findByTitle(@Param("title") String title);

	Collection<Movie> findByTitleLike(@Param("title") String title);

	@Query("MATCH (movie:Movie)<-[rel:ACTED_IN]-(person:Person) RETURN movie,rel,person LIMIT {limit}")
	Collection<Movie> graph(@Param("limit") int limit);
}
