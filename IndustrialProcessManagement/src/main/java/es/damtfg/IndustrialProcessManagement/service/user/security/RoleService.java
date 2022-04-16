/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.service.user.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.damtfg.IndustrialProcessManagement.model.user.security.Role;

/**
 * @author Alberto González
 *
 */
public interface RoleService {

	/**
	 * Búsqueda de todos los roles.
	 * 
	 * @return Iterable<Role>
	 */
	public Iterable<Role> findAll();
	
	/**
	 * Búsqueda de todos los roles.
	 * 
	 * @return List<Role>
	 */
	public List<Role> listAll();
	
	/**
	 * Búsqueda de todos los roles con paginación.
	 * 
	 * @param pageable
	 * 
	 * @return Page<Role>
	 */
	public Page<Role> findAll(Pageable pageable);
	
	/**
	 * Búsqueda de rol por ID.
	 * 
	 * @param id
	 * 
	 * @return Objeto Optional<Role>
	 */
	public Optional<Role> findById(Long id);
	
	/**
	 * Búsqueda de rol por nombre.
	 * 
	 * @param name
	 * 
	 * @return Objeto Optional<Role>
	 */
	public Optional<Role> findByName(String name);
	
	/**
	 * Crea la persistencia del objeto.
	 * 
	 * @param role
	 * 
	 * @return Objeto role guardado
	 */
	public Role save(Role role);
	
	/**
	 * Crea la persistencia de los objetos.
	 * 
	 * @param ArrayList<Role>
	 * 
	 * @return Lista de objetos guardados
	 */
	public List<Role> saveAll(ArrayList<Role> role);
	
	/**
	 * Elimina un role por ID.
	 * 
	 * @param id
	 */
	public void deleteById(Long id);
	
}
