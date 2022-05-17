package es.damtfg.IndustrialProcessManagement.service.production;

import es.damtfg.IndustrialProcessManagement.model.production.Proceso;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface ProcessService {

	public Boolean existsByName(String name);

	public Proceso save(Proceso process);

	ApiResponse create(Proceso proceso);

}
