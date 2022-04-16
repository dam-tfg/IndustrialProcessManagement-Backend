/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.repository.user.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.damtfg.IndustrialProcessManagement.model.user.security.Role;

/**
 * @author Alberto González
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * Búsqueda por ID del ROL.
	 * 
	 * @param id
	 * @return Optional<Role>
	 */
	Optional<Role> findById(String id);
	
	/**
	 * Búsqueda por nombre del ROL.
	 * 
	 * @param roleName
	 * @return Optional<Role>
	 */
	Optional<Role> findByName(String name);
		
}
