package es.damtfg.IndustrialProcessManagement.repository.production;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.production.Log;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface LogRepository extends JpaRepository <Log, Long> {

	/**
	 * Búsqueda por ID.
	 * 
	 * @param id
	 * @return Optional
	 */
	Optional<Log> findById(String id);
	

}
