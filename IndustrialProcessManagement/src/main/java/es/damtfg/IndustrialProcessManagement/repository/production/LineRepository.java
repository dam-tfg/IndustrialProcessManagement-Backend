package es.damtfg.IndustrialProcessManagement.repository.production;

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
	
	/**
	 * @param name
	 * 
	 */
	Boolean findByName(String name);

	Line save(Line line);

}
