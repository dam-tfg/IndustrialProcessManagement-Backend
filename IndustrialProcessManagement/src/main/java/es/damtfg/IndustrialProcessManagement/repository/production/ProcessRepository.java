package es.damtfg.IndustrialProcessManagement.repository.production;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.production.Process;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface ProcessRepository extends JpaRepository <Process, Long> {
	
	Boolean findByName(String name);
	
	Process save(Process logEvent);

}
