/**
 *
 */
package demo.neo4j.entity;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author avinash
 *
 */
@NodeEntity
public class Person {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int born;

	@Relationship(type = "ACTED_IN")
	private List<Movie> movies;

	@Relationship(type = "FRIEND", direction = "OUTGOING")
	private List<Person> friends;

	public void knows(Person friend) {
		if (friends == null) {
			friends = new ArrayList<>();
		}
		friends.add(friend);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBorn() {
		return born;
	}

	public void setBorn(int born) {
		this.born = born;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public void addMovie(Movie movie) {
		if(movies==null) {
			movies = new ArrayList<>();
		}
		movies.add(movie);
	}
}
