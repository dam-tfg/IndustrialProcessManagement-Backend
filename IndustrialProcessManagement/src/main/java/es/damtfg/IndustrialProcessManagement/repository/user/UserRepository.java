/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.user.User;

/**
 * @author Alberto González
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * Busca por nombre de usuario.
	 * 
	 * @param username
	 * 
	 * @return Optional<User>
	 */
	Optional<User> findByUsername(String username);
	
	/**
	 * Busca por correo electrónico.
	 * 
	 * @param email
	 * 
	 * @return
	 */
	Optional<User> findByEmail(String email);
	
	/**
	 * Busca por nombre de usuario o correo electrónico.
	 * 
	 * @param username
	 * @param email
	 * 
	 * @return
	 */
	Optional<User> findByUsernameOrEmail(String username, String email);
	
	/**
	 * Comprueba la existencia de un nombre de usuario.
	 * 
	 * @param username
	 * 
	 * @return
	 */
	Boolean existsByUsername(String username);

	/**
	 * Comprueba la existencia de un email.
	 * 
	 * @param email
	 * 
	 * @return
	 */
    Boolean existsByEmail(String email);
    
}
