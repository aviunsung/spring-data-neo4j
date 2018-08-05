/**
 *
 */
package demo.neo4j.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import demo.neo4j.Neo4JCrudApplication;
import demo.neo4j.entity.Movie;
import demo.neo4j.entity.Person;
import demo.neo4j.entity.Role;

/**
 * @author avinash
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Neo4JCrudApplication.class)
@Transactional
public class MovieRepositoryTest {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private MovieRepository movieRepository;

	@Before
	public void setUp() {
		Person rajkumar = new Person();
		rajkumar.setName("RajKumar Rao");
		rajkumar.setBorn(1986);

		Person ayushmann = new Person();
		ayushmann.setName("Ayushmann Khurana");
		ayushmann.setBorn(1988);

		Person shraddha = new Person();
		shraddha.setName("Shraddha Kapoor");
		shraddha.setBorn(1990);

		rajkumar.knows(ayushmann);
		rajkumar.knows(shraddha);
		personRepository.save(rajkumar);

		Movie shahid = new Movie();
		shahid.setTitle("Shahid");
		shahid.setReleased(2012);
		movieRepository.save(shahid);

		Role azam = new Role();
		azam.setMovie(shahid);
		azam.setPerson(rajkumar);
		azam.addRoleName("Azam");

		shahid.addRole(azam);
		movieRepository.save(shahid);

		rajkumar.addMovie(shahid);
		personRepository.save(rajkumar);
	}

	@Test
	public void testFindPersonByName() {
		String name = "RajKumar Rao";
		Person person = personRepository.findByName(name);
		assertNotNull(person);
		assertEquals(name, person.getName());
	}

	@Test
	public void testGetFriendsByName() {
		String name = "RajKumar Rao";
		Collection<Person> persons = personRepository.getFriends(name);
		assertNotNull(persons);
		assertEquals(2, persons.size());
	}

	@Test
	public void testPersonMovieGraph() {
		Collection<Person> persons = personRepository.graph(5);
		assertNotNull(persons);
		assertEquals(1, persons.size());
		Person person = persons.iterator().next();
		assertEquals("Shahid", person.getMovies().iterator().next().getTitle());
		assertEquals("RajKumar Rao", person.getName());
	}

	@Test
	public void testFindMovieByTitle() {
		String title = "Shahid";
		Movie movie = movieRepository.findByTitle(title);
		assertNotNull(movie);
		assertEquals(title, movie.getTitle());
	}

	@Test
	public void testFindMovieByTitleLike() {
		String title = "Shahid";
		Collection<Movie> movies = movieRepository.findByTitleLike(title);
		assertNotNull(movies);
		assertEquals(1, movies.size());
	}

	@Test
	public void testMovieGraph() {
		Collection<Movie> movies = movieRepository.graph(5);
		assertNotNull(movies);
		assertEquals(1, movies.size());
		Movie movie=movies.iterator().next();
		assertEquals(1, movie.getRoles().size());
		assertEquals("Shahid", movie.getTitle());
		assertEquals("Azam", movie.getRoles().iterator().next().getRoles().iterator().next());
		assertEquals("RajKumar Rao", movie.getRoles().iterator().next().getPerson().getName());
	}
}
