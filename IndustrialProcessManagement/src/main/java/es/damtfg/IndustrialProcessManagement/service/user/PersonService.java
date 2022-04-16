/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.service.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.damtfg.IndustrialProcessManagement.model.user.Person;

/**
 * @author Alberto González
 *
 */
public interface PersonService {

	/**
	 * Búsqueda de todas las personas.
	 * 
	 * @return
	 */
	public Iterable<Person> findAll();
	
	/**
	 * Búsqueda de todas las personas con paginación.
	 * 
	 * @param pageable
	 * 
	 * @return
	 */
	public Page<Person> findAll(Pageable pageable);
	
	/**
	 * Búsqueda de una persona por ID.
	 * 
	 * @param id
	 * 
	 * @return Objeto Optional<User>
	 */
	public Optional<Person> findById(Long id);
		
	/**
	 * Crea la persistencia del objeto.
	 * 
	 * @param person
	 * 
	 * @return Objeto persona guardado
	 */
	public Person save(Person person);
	
	/**
	 * Elimina una persona por ID.
	 * 
	 * @param id
	 */
	public void deleteById(Long id);
	    
}
