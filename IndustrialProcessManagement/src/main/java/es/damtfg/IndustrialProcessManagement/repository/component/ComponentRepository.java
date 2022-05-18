package es.damtfg.IndustrialProcessManagement.repository.component;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.component.Component;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface ComponentRepository extends JpaRepository <Component, Long> {
	
	/**
	 * Busca por nombre de producto.
	 * 
	 * @param name
	 * 
	 * @return Optional<Component>
	 */
	Boolean findByName(String name);
	
	/**
	 * Búsqueda por ID.
	 * 
	 * @param id
	 * @return Optional
	 */
	Optional<Component> findById(String id);

}
