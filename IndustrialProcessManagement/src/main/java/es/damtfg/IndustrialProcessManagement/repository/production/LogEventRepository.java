package es.damtfg.IndustrialProcessManagement.repository.production;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.production.LogEvent;

/**
 * @author Alberto González
 * @author Carlos Munoz
 *
 */
@Repository
public interface LogEventRepository extends JpaRepository <LogEvent, Long> {
	
	/**
	 * Busca por nombre de producto.
	 * 
	 * @param name
	 * 
	 * @return Optional<LogEvent>
	 */
	Optional<LogEvent> findByName(String name);
	
	/**
	 * Comprueba la existencia de un nombre.
	 * 
	 * @param name
	 * 
	 * @return
	 */
	Boolean existsByName(String name);
	
	/**
	 * Búsqueda por ID.
	 * 
	 * @param id
	 * @return Optional
	 */
	Optional<LogEvent> findById(String id);

}
