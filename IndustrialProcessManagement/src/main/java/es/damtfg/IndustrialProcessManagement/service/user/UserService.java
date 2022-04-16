/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.service.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.damtfg.IndustrialProcessManagement.model.user.User;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * @author Alberto González
 *
 */
public interface UserService {

	/**
	 * Crea un nuevo usuario sin persistencia
	 * 
	 * @param user
	 * @return
	 */
	public ApiResponse create(User user);

	/**
	 * Búsqueda de todos los usuarios.
	 * 
	 * @return
	 */
	public Iterable<User> findAll();
	
	/**
	 * Búsqueda de todos los usuarios con paginación.
	 * 
	 * @param pageable
	 * 
	 * @return
	 */
	public Page<User> findAll(Pageable pageable);
	
	/**
	 * Búsqueda de usuario por ID.
	 * 
	 * @param id
	 * 
	 * @return Objeto Optional<User>
	 */
	public Optional<User> findById(Long id);
	
	/**
	 * Búsqueda de usuario por nombre de usuario.
	 * 
	 * @param username
	 * 
	 * @return Objeto Optional<User>
	 */
	public Optional<User> findByUsername(String username);
	
	/**
	 * Crea la persistencia del objeto.
	 * 
	 * @param user
	 * 
	 * @return Objeto usuario guardado
	 */
	public User save(User user);
	
	/**
	 * Elimina un usuario por ID.
	 * 
	 * @param id
	 */
	public void deleteById(Long id);
	
	/**
	 * Comprueba la existencia de un nombre de usuario.
	 * 
	 * @param username
	 * 
	 * @return
	 */
	public Boolean existsByUsername(String username);

	/**
	 * Comprueba la existencia de un email.
	 * 
	 * @param email
	 * 
	 * @return
	 */
    public Boolean existsByEmail(String email);
	
}
