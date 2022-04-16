/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.user.Person;

/**
 * @author Alberto González
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> { }
