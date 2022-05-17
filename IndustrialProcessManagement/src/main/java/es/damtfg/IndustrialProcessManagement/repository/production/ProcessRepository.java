package es.damtfg.IndustrialProcessManagement.repository.production;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.production.Proceso;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface ProcessRepository extends JpaRepository <Proceso, Long> {
	
	Boolean findByName(String name);
	
	Proceso save(Proceso logEvent);

}
