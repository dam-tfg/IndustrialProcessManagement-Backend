package es.damtfg.IndustrialProcessManagement.repository.component;

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
	 * @return Optional<producto>
	 */
	Boolean findByName(String name);

}
