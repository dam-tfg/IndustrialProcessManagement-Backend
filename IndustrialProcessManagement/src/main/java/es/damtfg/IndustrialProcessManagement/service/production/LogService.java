package es.damtfg.IndustrialProcessManagement.service.production;

import es.damtfg.IndustrialProcessManagement.model.production.Log;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface LogService {

	public ApiResponse create(Log log);

	public Log save(Log log);

}
