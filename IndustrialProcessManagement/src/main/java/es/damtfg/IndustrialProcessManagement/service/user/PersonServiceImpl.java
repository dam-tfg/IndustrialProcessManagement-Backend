/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.user.Person;
import es.damtfg.IndustrialProcessManagement.repository.user.PersonRepository;

/**
 * @author Alberto González
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * Búsqueda de todas las personas.
	 * 
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Iterable<Person> findAll() {

		return personRepository.findAll();
	}

	/**
	 * Búsqueda de todas las personas con paginación.
	 * 
	 * @param pageable
	 * 
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Person> findAll(Pageable pageable) {

		return personRepository.findAll(pageable);
	}

	/**
	 * Búsqueda de persona por ID.
	 * 
	 * @param id
	 * 
	 * @return Objeto Optional<Person>
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Person> findById(Long id) {

		return personRepository.findById(id);
	}

	/**
	 * Crea la persistencia del objeto.
	 * 
	 * @param person
	 * 
	 * @return Objeto person guardado
	 */
	@Override
	@Transactional
	public Person save(Person person) {

		return personRepository.save(person);
	}

	/**
	 * Elimina un usuario por ID.
	 * 
	 * @param id
	 */
	@Override
	@Transactional
	public void deleteById(Long id) {
		
		personRepository.deleteById(id);
	}

}
