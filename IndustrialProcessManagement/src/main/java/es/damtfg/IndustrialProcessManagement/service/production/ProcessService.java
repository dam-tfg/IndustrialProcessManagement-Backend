package es.damtfg.IndustrialProcessManagement.service.production;

import es.damtfg.IndustrialProcessManagement.model.production.Process;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface ProcessService {

	public Boolean existsByName(String name);

	public Process save(Process process);

	ApiResponse create(Process proceso);

}
