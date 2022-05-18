package es.damtfg.IndustrialProcessManagement.repository.production;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.production.Line;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface LineRepository extends JpaRepository <Line, Long> {
	
	Boolean findByName(String name);
	
	/**
	 * Búsqueda por ID.
	 * 
	 * @param id
	 * @return Optional
	 */
	Optional<Line> findById(String id);

}
