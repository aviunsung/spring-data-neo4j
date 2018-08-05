/**
 *
 */
package demo.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import demo.neo4j.entity.Role;

/**
 * @author avinash
 *
 */
public interface RoleRepository extends Neo4jRepository<Role, Long> {

}
